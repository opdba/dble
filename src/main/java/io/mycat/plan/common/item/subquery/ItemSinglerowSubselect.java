/**
 *
 */
package io.mycat.plan.common.item.subquery;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import io.mycat.plan.common.field.Field;
import io.mycat.plan.common.item.Item;
import io.mycat.plan.common.time.MySQLTime;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class ItemSinglerowSubselect extends ItemSubselect {
    /* 记录一行的row */
    private List<Item> row;
    /* 记录row item相关的fields值 */
    private List<Field> fields;
    private Item value;
    private boolean noRows;

    public ItemSinglerowSubselect(String currentDb, SQLSelectQuery query) {
        super(currentDb, query);
    }

    @Override
    public SubSelectType substype() {
        return SubSelectType.SINGLEROW_SUBS;
    }

    @Override
    public void reset() {
        this.nullValue = true;
        if (value != null)
            value.setNullValue(true);
    }

    @Override
    public BigDecimal valReal() {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.valReal();
        } else {
            reset();
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigInteger valInt() {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.valInt();
        } else {
            reset();
            return BigInteger.ZERO;
        }
    }

    @Override
    public String valStr() {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.valStr();
        } else {
            reset();
            return null;
        }
    }

    @Override
    public BigDecimal valDecimal() {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.valDecimal();
        } else {
            reset();
            return null;
        }
    }

    @Override
    public boolean getDate(MySQLTime ltime, long fuzzydate) {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.getDate(ltime, fuzzydate);
        } else {
            reset();
            return true;
        }
    }

    @Override
    public boolean getTime(MySQLTime ltime) {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.getTime(ltime);
        } else {
            reset();
            return true;
        }
    }

    @Override
    public boolean valBool() {
        if (!noRows && !execute() && !value.isNullValue()) {
            nullValue = false;
            return value.valBool();
        } else {
            reset();
            return false;
        }
    }

    @Override
    public void fixLengthAndDec() {

    }

    @Override
    public SQLExpr toExpression() {
        // TODO
        return null;
    }

    @Override
    protected Item cloneStruct(boolean forCalculate, List<Item> calArgs, boolean isPushDown, List<Field> fieldList) {
        // TODO Auto-generated method stub
        return null;
    }

    /*--------------------------------------getter/setter-----------------------------------*/
    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
