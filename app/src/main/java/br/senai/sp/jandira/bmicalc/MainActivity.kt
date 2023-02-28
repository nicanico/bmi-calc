package br.senai.sp.jandira.bmicalc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.bmicalc.model.Client
import br.senai.sp.jandira.bmicalc.model.Product
import br.senai.sp.jandira.bmicalc.ui.theme.BMICalcTheme
import java.time.LocalDate
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BMICalcTheme {
                CalculatorScreen()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CalculatorScreen() {

    var weightState = rememberSaveable {
        mutableStateOf("")
    }
    var heightState = rememberSaveable {
        mutableStateOf("")
    }

    var bmiState = rememberSaveable {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //HEADER
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bmi),
                    contentDescription = "",
                    modifier = Modifier.size(120.dp)
                )
                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 30.sp,
                    color = Color.Blue,
                    letterSpacing = 8.sp
                )

            }
            // FORM
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.weight_label),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = weightState.value,
                    onValueChange = {
                        weightState.value = it
                        Log.i("ds2t", it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)


                )
                Text(
                    text = stringResource(id = R.string.height_label),
                    modifier = Modifier
                        .padding(
                            bottom = 8.dp,
                            top = 16.dp
                        )
                )
                OutlinedTextField(
                    value = heightState.value,
                    onValueChange = {
                        heightState.value = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        var bmi = 0.00
                        var w = weightState.value.toDouble()
                        var h = heightState.value.toDouble()
                        bmi = w / (h / 100).pow(2)
                        bmiState.value = bmi.toString()
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(Color(100, 190, 100))
                ) {
                    Text(
                        text = stringResource(id = R.string.button_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }

            }
            // FOOTER
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                    color = Color(
                        148,
                        0,
                        211
                    ),
                    shape = RoundedCornerShape(
                        topStart = 32.dp,
                        topEnd = 23.dp
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_score),
                            modifier = Modifier.padding(vertical = 10.dp),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = bmiState.value,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            text = stringResource(id = R.string.ideal),
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 18.sp

                        )
                        Row(

                        ) {
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = stringResource(id = R.string.reset))
                            }
                            Spacer(modifier = Modifier.width(48.dp))
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = stringResource(id = R.string.share))
                            }
                        }
                    }


                }
            }
        }
    }
}