package com.mcmouse88.stock_market_app.data.repository

import com.mcmouse88.stock_market_app.data.csv.CSVParser
import com.mcmouse88.stock_market_app.data.local.CompanyListingEntity
import com.mcmouse88.stock_market_app.data.local.StockDao
import com.mcmouse88.stock_market_app.data.mapper.toCompanyEntity
import com.mcmouse88.stock_market_app.data.mapper.toCompanyInfo
import com.mcmouse88.stock_market_app.data.mapper.toCompanyListing
import com.mcmouse88.stock_market_app.data.remote.StockApi
import com.mcmouse88.stock_market_app.domain.model.CompanyInfo
import com.mcmouse88.stock_market_app.domain.model.CompanyListing
import com.mcmouse88.stock_market_app.domain.model.IntraDayInfo
import com.mcmouse88.stock_market_app.domain.repository.StockRepository
import com.mcmouse88.stock_market_app.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val dao: StockDao,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intraDayInfoParser: CSVParser<IntraDayInfo>
) : StockRepository {

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListing = dao.searchCompanyListing(query)
            emit(
                Resource.Success(
                    data = localListing.map(CompanyListingEntity::toCompanyListing)
                )
            )

            val isDbEmpty = localListing.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = isDbEmpty.not() && fetchFromRemote.not()
            if (shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListing = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListing?.let { listings ->
                dao.clearCompanyListing()
                dao.insertCompanyListing(listings.map(CompanyListing::toCompanyEntity))
                emit(
                    Resource.Success(
                        data = dao
                            .searchCompanyListing("")
                            .map(CompanyListingEntity::toCompanyListing)
                    )
                )
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getIntraDayInfo(symbol: String): Resource<List<IntraDayInfo>> {
        return try {
            val response = api.getIntraDayInfo(symbol)
            val result = intraDayInfoParser.parse(response.byteStream())
            Resource.Success(result)
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Couldn't load intra day info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Couldn't load intra day info")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resource.Success(result.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error("Couldn't load company info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error("Couldn't load company info")
        }
    }
}