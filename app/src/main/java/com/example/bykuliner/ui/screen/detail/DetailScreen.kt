package com.example.bykuliner.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bykuliner.R
import com.example.bykuliner.data.ViewModelFactory
import com.example.bykuliner.di.injection
import com.example.bykuliner.ui.common.UiState
import com.example.bykuliner.ui.components.rating
import com.example.bykuliner.ui.theme.ByKulinerTheme
import com.example.bykuliner.ui.theme.Shapes


@Composable
fun DetailScreen(
    kulinerId: Int,
    viewModel: DetailKulinerViewModel = viewModel(
        factory = ViewModelFactory(
            injection.providerRepository()
        )
    ),
    navigateBack: () -> Unit,

)
{
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{uiState ->
        when (uiState){
            is UiState.Loading -> {
                viewModel.getKulinerById(kulinerId)
            }
            is UiState.Success ->{
                val data = uiState.data
                DetailContent(
                    image = data.kuliner.image,
                    name = data.kuliner.name,
                    region = data.kuliner.region,
                    description = data.kuliner.description,
                    ranking = data.kuliner.ranking,
                    navigateBack = navigateBack
                )
            }
            is UiState.Error -> {}
        }

    }

}
@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    region: String,
    description: String,
    ranking: String,
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier
 ){
    val stars = mutableListOf<rating>()
    for (i in 0 until ranking.toInt()) run {
        stars.add(
            rating(i, true)
        )
    }


    Column(modifier = modifier.padding(20.dp)) {
        Row( horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        Row( horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(Shapes.medium)
            )
        }
           LazyRow(
               modifier = Modifier.padding(start = 12.dp, bottom = 16.dp)
           ) {
               items(stars, key = {it.id}){
                   Icon(
                       imageVector = Icons.Default.Star,
                       contentDescription = null,
                       modifier = Modifier
                           .size(20.dp)
                   )
               }
           }
        Row {
            Text(
                text = stringResource(R.string.region),
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 12.dp, end = 6.dp, bottom = 8.dp)
            )
            Text(
                text = region,
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(start = 12.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DetailKulinerPreview(){
    ByKulinerTheme {
        DetailContent(
            R.drawable.kuliner_1,"Nasi Goreng","Seluruh Indonesia","Nasi goreng adalah hidangan nasi yang digoreng dalam minyak atau margarin, yang biasanya ditambahkan kecap manis, bawang merah, bawang putih, tauge, ayam, dan bumbu-bumbu lainnya.","5",
            navigateBack = { })
    }
}