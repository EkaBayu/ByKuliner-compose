package com.example.bykuliner.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bykuliner.data.ViewModelFactory
import com.example.bykuliner.di.injection
import com.example.bykuliner.model.GetKuliner
import com.example.bykuliner.ui.common.UiState
import com.example.bykuliner.ui.components.KulinerItems

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
        viewModel: HomeViewModel = viewModel(
            factory = ViewModelFactory(injection.providerRepository())
        ),
    navigateToDetail: (Int) -> Unit,
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading ->{
                viewModel.getAllKuliner()
            }
            is UiState.Success ->{
                HomeContent(
                    kuliner = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,

                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    kuliner: List<GetKuliner>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,

){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier){
        items(kuliner){data ->
            KulinerItems(
                name = data.kuliner.name,
                region = data.kuliner.region,
                description = data.kuliner.description,
                ranking = data.kuliner.ranking,
                image = data.kuliner.image,
                modifier = Modifier.clickable{
                    navigateToDetail(data.kuliner.id)
                }

            )
        }
    }
}