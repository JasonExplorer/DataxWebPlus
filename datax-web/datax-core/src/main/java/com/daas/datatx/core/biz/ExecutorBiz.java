package com.daas.datatx.core.biz;

import com.daas.datatx.core.biz.model.LogResult;
import com.daas.datatx.core.biz.model.ReturnT;
import com.daas.datatx.core.biz.model.TriggerParam;

/**
 * @author  xuxueli on 17/3/1.
 */
public interface ExecutorBiz {

    /**
     * beat
     *
     * @return
     */
    ReturnT<String> beat();

    /**
     * idle beat
     *
     * @param jobId
     * @return
     */
    ReturnT<String> idleBeat(int jobId);

    /**
     * kill
     *
     * @param jobId
     * @return
     */
    ReturnT<String> kill(int jobId);

    /**
     * log
     *
     * @param logDateTim
     * @param logId
     * @param fromLineNum
     * @return
     */
    ReturnT<LogResult> log(long logDateTim, long logId, int fromLineNum);

    /**
     * run
     *
     * @param triggerParam
     * @return
     */
    ReturnT<String> run(TriggerParam triggerParam);
}
