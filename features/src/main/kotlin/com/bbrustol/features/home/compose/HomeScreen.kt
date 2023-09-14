package com.bbrustol.features.home.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bbrustol.features.home.compose.utils.HeadlineListPreviewParamProvider
import com.bbrustol.features.home.model.UsersModel

@Composable
fun UsersScreen(usersModel: List<UsersModel>, navController: NavHostController) {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(usersModel.size) {
            CardUsers(usersModel[it], it, navController)
        }
    }
}

//@Composable
//fun DetailsScreen(
//    usersModel: UsersModel,
//    modifier: Modifier = Modifier,
//) {
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val requester = FocusRequester()
//
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_RESUME) {
//                requester.requestFocus()
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
//    }
//    with(usersModel) {
//        val context = LocalContext.current
//        val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }
//
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .verticalScroll(rememberScrollState())
//        ) {
//            LoadImage(
//                imageUrl = urlToImage,
//                contentDescription = null,
//                placeholder = UIKIT_R.drawable.newspaper,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(LocalConfiguration.current.getHeight(2))
//                    .clip(RoundedCornerShape(dimensionResource(id = UIKIT_R.dimen.margin_normal))),
//                contentScale = ContentScale.Crop
//            )
//
//            Column(
//                modifier = Modifier.padding(
//                    top = 0.dp,
//                    start = dimensionResource(id = UIKIT_R.dimen.margin_double),
//                    end = dimensionResource(id = UIKIT_R.dimen.margin_double),
//                    bottom = dimensionResource(id = UIKIT_R.dimen.margin_double)
//                )
//
//            ) {
//
//                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
//                    val (author, publishedAt) = createRefs()
//
//                    Text(
//                        usersModel.author,
//                        style = MaterialTheme.typography.bodySmall,
//                        modifier = Modifier.constrainAs(author) {
//                            top.linkTo(parent.top, margin = 8.dp)
//                        }
//                    )
//                    Text(
//                        usersModel.publishedAt.formatDate(TimeFormatType.DDMMMYYYY_HHMM.pattern),
//                        style = MaterialTheme.typography.bodySmall,
//                        modifier = Modifier.constrainAs(publishedAt) {
//                            top.linkTo(author.top)
//                            linkTo(author.end, parent.end, bias = 1F)
//                            width = Dimension.preferredWrapContent
//                        }
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))
//
//                Text(
//                    text = title,
//                    style = MaterialTheme.typography.headlineMedium,
//                    modifier = Modifier
//                        .semantics { heading() }
//                        .focusRequester(requester)
//                        .focusable()
//                )
//
//                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))
//
//                Text(
//                    text = description,
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_double)))
//
//                if (!content.isNullOrEmpty()) {
//                    Text(
//                        text = content,
//                        style = MaterialTheme.typography.bodyLarge
//                    )
//
//                    Spacer(modifier = Modifier.height(dimensionResource(id = UIKIT_R.dimen.margin_normal)))
//                }
//
//                OutlinedButton(
//                    onClick = { context.startActivity(intent) },
//                    shape = RoundedCornerShape(5.dp),
//                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
//                ) {
//                    Text(
//                        text = stringResource(id = UIKIT_R.string.details_screen_read_more),
//                        fontWeight = FontWeight.Bold,
//                        color = MaterialTheme.colorScheme.primary
//                    )
//                }
//            }
//        }
//    }
//}

@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun HeadlineScreenPreview(
    @PreviewParameter(HeadlineListPreviewParamProvider::class) usersModel: List<UsersModel>
) {
    UsersScreen(usersModel, rememberNavController())
}


//@Preview(showBackground = true, backgroundColor = 0xffffff)
//@Composable
//fun HeadlineDetailsScreenPreview(
//    @PreviewParameter(HeadlinePreviewParamProvider::class) usersModel: UsersModel
//) {
//    DetailsScreen(usersModel)
//}
