package com.example.bykuliner.data

import com.example.bykuliner.model.FakeKulinerDataSource
import com.example.bykuliner.model.GetKuliner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class KulinerRepository {
    private val kulinerList = mutableListOf<GetKuliner>()

    init {
        if (kulinerList.isEmpty()) {

            FakeKulinerDataSource.dummyKuliner.forEach {
                kulinerList.add(GetKuliner(it))
            }
        }
    }
    fun getAllKuliner(): Flow<List<GetKuliner>> {
        return flowOf(kulinerList)
    }
    fun getKulinerById(kulinerId: Int): GetKuliner {
        return kulinerList.first{
            it.kuliner.id == kulinerId
        }

    }

    companion object{
        @Volatile
        private var instance: KulinerRepository? = null

        fun getInstance(): KulinerRepository =
            instance ?: synchronized(this){
                KulinerRepository().apply {
                    instance = this
                }
            }
    }

}