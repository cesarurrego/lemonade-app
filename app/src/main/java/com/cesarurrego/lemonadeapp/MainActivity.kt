package com.cesarurrego.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cesarurrego.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeAppTheme {
        LemonadeImage(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun LemonadeImage(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }
    var squeezeTimes by remember { mutableIntStateOf((2..4).random()) }
    val lemonadeImage = when (currentStep) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val lemonadeLabel = when (currentStep) {
        1 -> R.string.tap_lemon_tree_label
        2 -> R.string.keep_tapping_lemon_label
        3 -> R.string.tap_lemonade_label
        else -> R.string.tap_empty_glass_label
    }

    val lemonadeImageContentDescription = when (currentStep) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.glass_lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                if (currentStep == 2 && squeezeTimes > 1) {
                    squeezeTimes--
                } else if (currentStep == 4) {
                    currentStep = 1
                    squeezeTimes = (2..4).random()
                } else {
                    currentStep = (currentStep + 1)
                }
            },
            shape = RoundedCornerShape(16),
            colors = ButtonDefaults.buttonColors(
                contentColor = colorResource(id = R.color.surf_crest),
                containerColor = colorResource(id = R.color.surf_crest),
            )
        ) {
            Image(
                painter = painterResource(lemonadeImage),
                contentDescription = stringResource(lemonadeImageContentDescription),
                modifier = Modifier.wrapContentSize()
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(lemonadeLabel, squeezeTimes),
            modifier = modifier
        )
    }
}

