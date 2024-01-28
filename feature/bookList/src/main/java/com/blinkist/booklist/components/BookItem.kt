package com.blinkist.booklist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.blinkist.booklist.R
import com.blinkist.core.model.Book
import org.threeten.bp.LocalDate

@Composable
fun BookItem(
    book: Book,
    onItemClick: (Book) -> Unit
) {
    Row(
        modifier =
        Modifier
            .fillMaxWidth()
            .clickable { onItemClick(book) },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White.copy(alpha = 0.1f),
        ) {
            AsyncImage(model = book.coverImageUrl,
                contentDescription = "small avatar picture",
                modifier = Modifier
                    .size(30.dp)
                    .padding(2.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.baseline_book)
            )
        }
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .padding(2.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = book.name,
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(textDirection = TextDirection.ContentOrLtr),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )
            Text(
                text = book.author,
                modifier = Modifier.wrapContentWidth(),
                style = TextStyle(textDirection = TextDirection.ContentOrLtr),
                color = Color.Black,
                maxLines = 1,
            )
        }
    }

}
@Preview
@Composable
private fun BookItem() {
    val book = Book(
        id = "1",
        name =" title of book",
        author = "myself",
        publishDate = LocalDate.now(),
        coverImageUrl = "https://images.blinkist.com/images/books/5694b3802f6827000700002a/3_4/640.jpg"
    )
    BookItem(book = book, onItemClick = {})
}