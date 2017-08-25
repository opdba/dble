package io.mycat.manager.response;

import io.mycat.manager.ManagerConnection;
import io.mycat.net.mysql.OkPacket;
import io.mycat.statistic.stat.UserStat;
import io.mycat.statistic.stat.UserStatAnalyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public final class ReloadUserStat {
    private ReloadUserStat() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ReloadUserStat.class);

    public static void execute(ManagerConnection c) {

        Map<String, UserStat> statMap = UserStatAnalyzer.getInstance().getUserStatMap();
        for (UserStat userStat : statMap.values()) {
            userStat.reset();
        }

        LOGGER.warn(String.valueOf(c) + "Reset show @@sql  @@sql.sum  @@sql.slow  @@sql.high  @@sql.large  @@sql.resultset success by manager");

        OkPacket ok = new OkPacket();
        ok.setPacketId(1);
        ok.setAffectedRows(1);
        ok.setServerStatus(2);
        ok.setMessage("Reset show @@sql  @@sql.sum @@sql.slow  @@sql.high  @@sql.large  @@sql.resultset  success".getBytes());
        ok.write(c);
    }

}
