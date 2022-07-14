package com.theboringdevelopers.smartmurmansk.activity.main.celebration

import com.theboringdevelopers.smartmurmansk.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import javax.inject.Inject
import java.util.concurrent.TimeUnit


@HiltViewModel
class CelebrationViewModel@Inject constructor(

) : BaseViewModel() {

    fun party(): Party {
        return Party(
            speed = 0f,
            maxSpeed = 15f,
            damping = 0.9f,
            angle = Angle.BOTTOM,
            spread = Spread.ROUND,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(100),
            position = Position.Relative(0.0, 0.0).between(Position.Relative(1.0, 0.0))
        )
    }
}