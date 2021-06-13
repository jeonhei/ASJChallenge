package me.lam.asjchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
            //改变SystemBar颜色
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.White,
                    darkIcons = useDarkIcons
                )
            }

            ASJChallengeTheme {
                // A surface container using the 'background' color from the theme
                ProvideWindowInsets {//添加System padding
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
    var value by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().padding(top = 64.dp)) {
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
                value = value,
                onValueChange = {
                    value = it
                },
                maxLines = 1,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(5f),
                label = {
                    Text("请输入搜索关键字")
                }
            )

            TextButton(
                onClick = {  },
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                Text(text = "搜索")
            }
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