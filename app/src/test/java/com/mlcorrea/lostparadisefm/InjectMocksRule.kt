package com.mlcorrea.lostparadisefm

import io.mockk.MockKAnnotations
import org.junit.rules.TestRule

/**
 * Created by manuel on 08/04/19
 */
class InjectMocksRule {

    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockKAnnotations.init(testClass)
            statement
        }
    }
}
