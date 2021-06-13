package me.lam.asjchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import me.lam.asjchallenge.ui.theme.ASJChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ASJChallengeTheme {
                //改变SystemBar颜色
                rememberSystemUiController().setSystemBarsColor(
                    color = MaterialTheme.colors.background,
                    darkIcons = MaterialTheme.colors.isLight
                )

                ProvideWindowInsets {//添加System padding
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background) {
                        SearchFrame()
                    }
                }
            }
        }
    }
}

@Composable
fun SearchFrame() {
//    val searchDatas = listOf("an", "aback", "abase", "abash", "acid", "ant", "bob", "bean", "back", "cat", "dog", "ear")
    var keyword by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 64.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_jetpack_compose),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp, 100.dp),
            contentDescription = null)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {

            OutlinedTextField(
                value = keyword,
                onValueChange = {
                    keyword = it
                },
                maxLines = 1,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(5f),
                label = {
                    Text("请输入搜索关键字")
                },
            )

            TextButton(
                onClick = {  },
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                Text(text = "搜索")
            }
        }

//        SearchHintList(keyword = keyword)

//        val resultDatas by remember { mutableStateOf(searchDatas.filter { it.contains(keyword) }) }
//        SearchHintList(resultDatas)
    }
}

@Composable
fun SearchHintList(results: List<String>) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(count = results.size) {
            Text(text = results[it])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ASJChallengeTheme {
        SearchFrame()
    }
}