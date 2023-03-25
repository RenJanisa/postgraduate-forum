package com.banner.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author rjj
 * @date 2023/3/11 - 15:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostgraduateForumException extends RuntimeException {

    private int status;
    private String errMessage;

    public static void error(CommonError commonError){
        throw new PostgraduateForumException(500,commonError.getErrMessage());
    }

    public static void error(int status,String errMessage){
        throw new PostgraduateForumException(status,errMessage);
    }


}
