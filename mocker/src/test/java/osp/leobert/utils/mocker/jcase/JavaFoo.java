package osp.leobert.utils.mocker.jcase;

import osp.leobert.utils.mocker.NotationFetcherTest;
import osp.leobert.utils.mocker.notation.MockIntRange;
import osp.leobert.utils.mocker.notation.repeat.MockIntRanges;

/**
 * Classname: JavaFoo </p>
 * Description: TODO </p>
 * Created by leobert on 2022/10/20.
 */
public class JavaFoo {

    @MockIntRange(from = 1)
    @MockIntRange(from = 2, groups = {NotationFetcherTest.class})
    @MockIntRange(from = 3, groups = {NotationFetcherTest.class, Integer.class})
    Integer aInt;

    @MockIntRanges(
            value = {
                    @MockIntRange(from = 1),
                    @MockIntRange(from = 2, groups = {NotationFetcherTest.class}),

                    @MockIntRange(from = 3, groups = {
                            NotationFetcherTest.class, Integer.class
                    })
            }
    )
    Integer aInt2;
}
