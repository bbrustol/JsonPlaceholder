package com.bbrustol.features.home.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.home.compose.utils.UsersPreviewParamProvider
import com.bbrustol.features.home.model.UsersModel
import com.bbrustol.uikit.compose.TextsCard
import com.bbrustol.uikit.utils.NavScreensType
import java.util.*

@Composable
fun CardUsers(usersModel: UsersModel, position: Int, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp, 8.dp, 0.dp)
            .clickable { navController.navigate(NavScreensType.HEADLINE_DETAILS.name + "/${position}") },
        shape = RoundedCornerShape(size = 4.dp),
        colors = cardColors(colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            with(usersModel) {
                Row {
                    Column(
                        modifier = Modifier
                            .padding(4.dp, 0.dp)
                            .align(Alignment.CenterVertically)
                            .weight(.6f)
                    ) {
                        TextsCard(content = "id: " + usersModel.id.toString())

                        TextsCard(content = "name: " + usersModel.name)

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HeadlineCardPreview(
    @PreviewParameter(UsersPreviewParamProvider::class) usersModel: UsersModel
) {
    CardUsers(usersModel, 0, rememberNavController())
}
