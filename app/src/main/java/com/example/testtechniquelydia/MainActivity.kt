package com.example.testtechniquelydia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testtechniquelydia.ui.screen.ContactApp
import com.example.testtechniquelydia.ui.theme.TestTechniqueLydiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTechniqueLydiaTheme {
                ContactApp()
            }
        }
    }
}