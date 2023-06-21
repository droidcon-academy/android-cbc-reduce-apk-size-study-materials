import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.droidcon.bmicalculator.ui.composable.bmi.BMIViewModel
import kotlin.math.roundToInt

enum class Gender {
    MALE, FEMALE
}

@Composable
fun GenderCard(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(if (selected) Color(0xFF1d1e33) else Color.Transparent)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text, color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SliderCard(
    modifier: Modifier,
    value: Int,
    onValueChange: (Float) -> Unit,
    minValue: Float,
    maxValue: Float
) {
    Box(modifier = modifier.background(color = Color(0xFF1d1e33))) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Height: ${value}cm",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Slider(
                value = value.toFloat(),
                onValueChange = onValueChange,
                valueRange = minValue..maxValue
            )
        }
    }
}

@Composable
fun NumericCard(
    modifier: Modifier, title: String, value: Int, onIncrement: () -> Unit, onDecrement: () -> Unit
) {
    Box(modifier = modifier.background(color = Color(0xFF1d1e33))) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White, // Set the text color to white
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Center // Center align the text
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center // Center align the row content
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable(onClick = onDecrement)
                ) {
                    Text(text = "-", color = Color.White) // Set the text color to white
                }
                Text(
                    text = value.toString(), color = Color.White, // Set the text color to white
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable(onClick = onIncrement)
                ) {
                    Text(text = "+", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun BMIForm(
    viewModel: BMIViewModel,
    navController: NavController?=null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF04040c))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GenderCard(text = "MALE", selected = viewModel.isMaleSelected) {
                viewModel.selectGender(Gender.MALE)
            }
            GenderCard(text = "FEMALE", selected = viewModel.isFemaleSelected) {
                viewModel.selectGender(Gender.FEMALE)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(bottom = 16.dp)
                .background(color = Color(0xFF04040c))
        ) {
            SliderCard(
                modifier = Modifier.padding(16.dp),
                value = viewModel.userHeight.roundToInt(),
                onValueChange = { newValue -> viewModel.setHeight(newValue) },
                minValue = 100f,
                maxValue = 250f
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            NumericCard(modifier = Modifier
                .padding(end = 8.dp)
                .weight(1f),
                title = "Weight",
                value = viewModel.weight,
                onIncrement = { viewModel.incrementWeight() },
                onDecrement = { viewModel.decrementWeight() })
            NumericCard(modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f),
                title = "Age",
                value = viewModel.age,
                onIncrement = { viewModel.incrementAge() },
                onDecrement = { viewModel.decrementAge() })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .background(Color.Red)
                .clickable {
                    navController?.navigate("result/${viewModel.calculateBMI()}")
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Calculate",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewBMIForm() {
    val viewModel = BMIViewModel()
    BMIForm(viewModel = viewModel)
}