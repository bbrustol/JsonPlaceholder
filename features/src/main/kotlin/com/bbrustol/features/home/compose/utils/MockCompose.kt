package com.bbrustol.features.home.compose.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.bbrustol.features.home.model.UsersModel

class UsersPreviewParamProvider : PreviewParameterProvider<UsersModel> {
    override val values: Sequence<UsersModel> =
        sequenceOf(getHeadlineModelMock())
}

class HeadlineListPreviewParamProvider : PreviewParameterProvider<List<UsersModel>> {
    override val values: Sequence<List<UsersModel>> =
        sequenceOf(
            listOf(
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock(),
                getHeadlineModelMock()
            )
        )
}

private fun getHeadlineModelMock() = UsersModel(
    id = 0,
    name = "Leanne Graham",
)
