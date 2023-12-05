package com.example.bykuliner.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bykuliner.R
import com.example.bykuliner.ui.theme.ByKulinerTheme

@Composable
fun ProfileScreen(

) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(24.dp)
    ){
        Image(painter = painterResource(R.drawable.profile),
            contentDescription = null,
            modifier = Modifier
                .width(200.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Fit,
        )
        Column(
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Text(
                text = stringResource(R.string.name_profile),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 5.dp)
            ){
                Icon(imageVector =Icons.Default.MailOutline , contentDescription =null, modifier = Modifier.size(18.dp) )
                Text(
                    text = (stringResource(R.string.email_profile)),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                    ),
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 5.dp)
            ){
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    ByKulinerTheme {
        ProfileScreen()
    }
}