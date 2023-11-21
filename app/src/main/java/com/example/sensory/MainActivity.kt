package com.example.sensory

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware. SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sensory.ui.theme.Pink40
//import com.example.sensory.ui.sensoryTheme
import com.example.sensory.ui.theme. PurpleGrey80
import com.example.sensory.ui.theme.SensoryTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProximitySensor()
        }
    }
}



@Composable
fun ProximitySensor() {
    val ctx = LocalContext.current
    val sensorManager: SensorManager = ctx.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val proximitySensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
    val sensorStatus = remember {
        mutableStateOf("")
    }
    var color = remember {
        mutableStateOf(Color.Black) }

    val proximitySensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }

        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0f) {
                    sensorStatus.value = "Blisko"
                    color.value = Color.Red
                } else {
                    sensorStatus.value = "Daleko"
                    color.value = Color.Green
                }


            }
        }
    }
   sensorManager.registerListener(
       proximitySensorEventListener,
       proximitySensor,
       SensorManager.SENSOR_DELAY_NORMAL
   )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink40)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Jestes",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
        Text(
            text = sensorStatus.value,
            color = color.value,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            fontSize = 40.sp, modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "od ekranu",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            //fontFamily = FontFamily.Default,
            fontSize = 30.sp, modifier = Modifier.padding(5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    SensoryTheme {
        ProximitySensor()
    }
}