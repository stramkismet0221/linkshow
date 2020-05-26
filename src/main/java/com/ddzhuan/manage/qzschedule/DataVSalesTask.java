package com.ddzhuan.manage.qzschedule;

import com.ddzhuan.manage.service.datav.SalesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * DataV销售额定时任务
 *
 * @author likeke
 * @date 2019/11/07
 */
public class DataVSalesTask {

    Logger log = Logger.getLogger(DataVSalesTask.class);

    @Autowired
    private SalesService salesService;

    public void run() {
        Double sales = getSalesRandom();
        salesService.modifySalesTotal(1L, sales, null);
    }

    /**
     * 获取10-50之间随机数并保留两位有效数字
     *
     * @return 随机数
     */
    private Double getSalesRandom() {
        DecimalFormat dcmFmt = new DecimalFormat("0.00");
        Random rand = new Random();
        Double d = rand.nextDouble() * 40 + 10;
        return Double.valueOf(dcmFmt.format(d));
    }

}