package io.mycat.plan.common.item.function.operator.logic;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import io.mycat.plan.common.field.Field;
import io.mycat.plan.common.item.Item;

import java.math.BigInteger;
import java.util.List;

public class ItemCondOr extends ItemCond {

    public ItemCondOr(List<Item> args) {
        super(args);
    }

    @Override
    public final String funcName() {
        return "or";
    }

    @Override
    public Functype functype() {
        return Functype.COND_OR_FUNC;
    }

    @Override
    public BigInteger valInt() {
        for (Item item : list) {
            if (item.valBool()) {
                nullValue = false;
                return BigInteger.ONE;
            }
            if (item.isNullValue())
                nullValue = true;
        }
        return BigInteger.ZERO;
    }

    @Override
    public SQLExpr toExpression() {
        SQLExpr left = args.get(0).toExpression();
        SQLExpr right = args.get(1).toExpression();
        SQLExpr result = new SQLBinaryOpExpr(left, SQLBinaryOperator.BooleanOr, right);
        for (int i = 2; i < args.size(); i++) {
            SQLExpr rightAnother = args.get(i).toExpression();
            result = new SQLBinaryOpExpr(result, SQLBinaryOperator.BooleanOr, rightAnother);
        }
        return result;
    }

    @Override
    protected Item cloneStruct(boolean forCalculate, List<Item> calArgs, boolean isPushDown, List<Field> fields) {
        List<Item> newArgs = null;
        if (!forCalculate)
            newArgs = cloneStructList(args);
        else
            newArgs = calArgs;
        return new ItemCondOr(newArgs);
    }

}
