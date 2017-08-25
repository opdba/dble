package io.mycat.plan.common.item.function.primary;

import io.mycat.plan.common.item.Item;

public abstract class ItemFuncAdditiveOp extends ItemNumOp {

    public ItemFuncAdditiveOp(Item a, Item b) {
        super(a, b);
    }

    @Override
    public void resultPrecision() {
        decimals = Math.max(args.get(0).getDecimals(), args.get(1).getDecimals());
    }

}
