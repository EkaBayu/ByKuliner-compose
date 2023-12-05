package com.example.bykuliner.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bykuliner.R
import com.example.bykuliner.ui.theme.ByKulinerTheme
import com.example.bykuliner.ui.theme.Shapes

@Composable
fun KulinerItems(
    name: String,
    region: String,
    description: String,
    ranking: String,
    image: Int,
    modifier:Modifier =Modifier
){
    Column(
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .clip(Shapes.medium)
            )
        }
        Row(
            modifier = modifier.padding(bottom = 8.dp)
        ) {

            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .padding(start = 6.dp, end = 15.dp)
            )

            Text(
                text = ranking,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            Icon(imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
            )
        }
        Row {
            Text(
                text = stringResource(R.string.region),
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 6.dp, end = 6.dp, bottom = 8.dp)
            )
            Text(
                text = region,
                maxLines = 2,
                style = MaterialTheme.typography.titleSmall
            )
        }

    }
}
@Composable
@Preview(showBackground = true)
fun RewardItemPreview() {
    ByKulinerTheme{
        KulinerItems("Nasi Goreng","Seluruh Indonesia",
            "Nasi goreng adalah hidangan nasi yang digoreng dalam minyak atau margarin, yang biasanya ditambahkan kecap manis, bawang merah, bawang putih, tauge, ayam, dan bumbu-bumbu lainnya.",
            "5",R.drawable.kuliner_1)
    }
}