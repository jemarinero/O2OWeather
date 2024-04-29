package com.o2o.ui.features.splash

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.o2o.ui.features.splash.components.SplashAnimation

@Composable
fun SplashFragment(
    modifier: Modifier = Modifier,
    onAnimationFinished: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SplashAnimation(onAnimationFinished)
    }
}
