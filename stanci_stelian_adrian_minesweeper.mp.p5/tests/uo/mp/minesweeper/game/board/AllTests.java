package uo.mp.minesweeper.game.board;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConstructorTests.class, FlagTests.class, GetStatusTests.class, StepOnTests.class,
		UncoverWelcomeAreaTests.class, UnflagTests.class, UnveilTests.class })
public class AllTests {

}
