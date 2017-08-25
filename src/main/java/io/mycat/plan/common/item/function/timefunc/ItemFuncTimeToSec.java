package io.mycat.plan.common.item.function.timefunc;

import io.mycat.plan.common.item.Item;
import io.mycat.plan.common.item.function.ItemFunc;
import io.mycat.plan.common.item.function.primary.ItemIntFunc;
import io.mycat.plan.common.time.MySQLTime;

import java.math.BigInteger;
import java.util.List;

public class ItemFuncTimeToSec extends ItemIntFunc {

    public ItemFuncTimeToSec(List<Item> args) {
        super(args);
    }

    @Override
    public final String funcName() {
        return "time_to_sec";
    }

    @Override
    public BigInteger valInt() {
        MySQLTime ltime = new MySQLTime();
        if (getArg0Time(ltime))
            return BigInteger.ZERO;
        long seconds = ltime.getHour() * 3600L + ltime.getMinute() * 60 + ltime.getSecond();
        return BigInteger.valueOf(ltime.isNeg() ? -seconds : seconds);
    }

    @Override
    public void fixLengthAndDec() {
        maybeNull = true;
        fixCharLength(10);
    }

    @Override
    public ItemFunc nativeConstruct(List<Item> realArgs) {
        return new ItemFuncTimeToSec(realArgs);
    }

}
