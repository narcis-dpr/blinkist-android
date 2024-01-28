package com.blinkist.booklist.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.blinkist.booklist.R
import com.blinkist.theme.Icons

@Composable
fun SortSection(
    sortOrder: SortOrder,
    sortBy: SortBy,
    onChangeSortOrder: (SortOrder) -> Unit,
    onChangeSortBy: (SortBy) -> Unit,
    modifier: Modifier = Modifier,
) {
    var shouldShowSortSection by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            SortButton(
                isSortSectionShown = shouldShowSortSection,
                onClick = { shouldShowSortSection = !shouldShowSortSection },
                colors = IconButtonDefaults.iconButtonColors(contentColor = MediaHeaderContentColor),
            )
        }

        CompositionLocalProvider(LocalContentColor provides MediaHeaderContentColor) {
            AnimatedVisibility(visible = shouldShowSortSection) {
                SortSection(
                    sortOrder = sortOrder,
                    sortBy = sortBy,
                    onChangeSortOrder = onChangeSortOrder,
                    onChangeSortBy = onChangeSortBy,
                    radioButtonColors = RadioButtonDefaults.colors(
                        selectedColor = MediaHeaderContentColor,
                        unselectedColor = MediaHeaderContentColor.copy(alpha = 0.75f),
                    ),
                )
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SortButton(
    isSortSectionShown: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.primary),
) {
    val rotateValue by animateFloatAsState(
        targetValue = if (isSortSectionShown) SortRotateValue else 0f,
        label = "RotateAnimation",
    )

    IconButton(modifier = modifier, onClick = onClick, colors = colors) {
        AnimatedContent(
            modifier = Modifier.rotate(rotateValue),
            targetState = isSortSectionShown,
            transitionSpec = { scaleIn() with scaleOut() },
            label = "SortIconAnimation",
        ) { targetIsSortSectionShown ->
            if (targetIsSortSectionShown) {
                Icon(
                    imageVector = Icons.Close.imageVector,
                    contentDescription = stringResource(id = R.string.close),
                )
            } else {
                Icon(
                    painter = painterResource(id = Icons.Sort.resourceId),
                    contentDescription = stringResource(id = R.string.sort),
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SortSection(
    sortOrder: SortOrder,
    sortBy: SortBy,
    onChangeSortOrder: (SortOrder) -> Unit,
    onChangeSortBy: (SortBy) -> Unit,
    modifier: Modifier = Modifier,
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.sort_order),
            style = MaterialTheme.typography.titleMedium,
        )

        Row {
            RadioButtonText(
                modifier = Modifier.weight(1f),
                textRes = R.string.ascending,
                isSelected = sortOrder == SortOrder.ASCENDING,
                onClick = { onChangeSortOrder(SortOrder.ASCENDING) },
                colors = radioButtonColors,
            )
            RadioButtonText(
                modifier = Modifier.weight(1f),
                textRes = R.string.descending,
                isSelected = sortOrder == SortOrder.DESCENDING,
                onClick = { onChangeSortOrder(SortOrder.DESCENDING) },
                colors = radioButtonColors,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = R.string.sort_by),
            style = MaterialTheme.typography.titleMedium,
        )

        FlowRow(maxItemsInEachRow = 2) {
            SortBy.values().forEach { sortByItem ->
                RadioButtonText(
                    modifier = Modifier.weight(1f),
                    textRes = sortByStringResourcesMap.getValue(sortByItem),
                    isSelected = sortBy == sortByItem,
                    onClick = { onChangeSortBy(sortByItem) },
                    colors = radioButtonColors,
                )
            }
        }
    }
}

private val sortByStringResourcesMap = mapOf(
    SortBy.TITLE to R.string.title,
    SortBy.DATE to R.string.date,
)

private val MediaHeaderContentColor = Color.White

private const val SortRotateValue = 180f
