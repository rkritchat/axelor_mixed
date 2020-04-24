package com.kline.communication.exception;

import com.kline.communication.constant.CommunicationConstant;

public class KLineException extends Exception {
    public KLineException(CommunicationConstant.ERROR err) {
        super(err.getMessage());
    }

    public KLineException(CommunicationConstant.ERROR err, Throwable e) {
        super(err.getMessage(), e);
    }
}
