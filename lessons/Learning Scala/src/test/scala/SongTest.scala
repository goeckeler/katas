/**
 * Created by tgo on 24.06.2014.
 */
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

class SongTest {
  @RunWith(classOf[JUnitRunner])
  class MyNumber extends FunSuite {

    test("add one and one"){
      assert(1 + 2 === 2,"Did not match");
    }
  }
}
