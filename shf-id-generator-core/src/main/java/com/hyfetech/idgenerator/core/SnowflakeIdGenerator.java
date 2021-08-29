package com.hyfetech.idgenerator.core;

import org.springframework.stereotype.Service;

/**
 * 雪花算法ID生成器
 * @author shanghaifei
 * @date 2021/8/25
 */
@Service
public class SnowflakeIdGenerator {
    //region 变量

    /**
     * 雪花算法
     * 符号位（1位）+时间戳（41位）+机房ID（5位）+序号（12位）
     */
    int timeStampBits = 41,machineRoomIdBits = 5,machineIdBits = 5,sequenceBits = 12;

    /**
     * 偏移量
     */
    int timeStampOffset = (machineRoomIdBits + machineIdBits + sequenceBits),
        machineRoomOffset = (machineIdBits + sequenceBits),
        machineIdOffset = (sequenceBits);

    /**
     * 最大机房ID、最大机器ID、最大序号
     */
    long maxMachineRoomId = -1L ^ (-1L << machineRoomIdBits),maxMachineId = -1L ^ (-1L << machineIdBits),
            maxSequenceId = -1L ^ (-1L << sequenceBits);

    /**
     * Thu Nov 04 2010 09:42:54 GMT+0800 (中国标准时间)
     * 开始时间戳，之所以用当前时间戳 - 开始时间戳，是为了让存入的时间戳在41位的范围之内
     */
    long timeStampStart = 1288834974657L;

    /**
     * 上次生成ID的时间（毫秒数）
     */
    long lastTimeMills = 0;

    /**
     * 序号
     */
    long sequence = 0;

    //endregion

    /**
     * 生成ID
     * @param machineRoomId 机房ID
     * @param machineId 机器ID
     * @return 生成的ID
     * @throws Exception
     */
    public synchronized Long generateId(Long machineRoomId,Long machineId) throws Exception {
        machineRoomId = 1L;
        machineId = 1L;
        if(machineRoomId > maxMachineRoomId) {
            throw new Exception("机房ID超过最大值");
        }
        if(machineId > maxMachineId) {
            throw new Exception("机器ID超过最大值");
        }
        long currentTimeMillis = getCurrentTimeMillis();
        if(currentTimeMillis < lastTimeMills) {
            //region 当前时间小于上一次生成时间，说明发生了时钟回拨

            long diffMills = lastTimeMills - currentTimeMillis;
            // 如果时间差小于5毫秒，则进行双倍的时间等待
            if(diffMills < 5) {
                wait(diffMills << 1);
            }
            currentTimeMillis = getCurrentTimeMillis();
            if(currentTimeMillis < lastTimeMills) {
                throw new Exception("无法生成ID！当前时间小于上一次生成ID的时间，时间差："
                        + (lastTimeMills - currentTimeMillis) + "毫秒");
            }

            //endregion
        }
        if(currentTimeMillis == lastTimeMills) {
            //region 当前时间等于上一次生成时间，需要判断序号是不是溢出，超过了最大值

            sequence = (sequence + 1) & maxSequenceId;
            if(sequence == 0) {
                // 说明sequence溢出
                while (currentTimeMillis == lastTimeMills) {
                    currentTimeMillis = getCurrentTimeMillis();
                }
                sequence = 1;
            }

            //endregion
        }
        else {
            //region 当前时间大于上一次生成时间

            sequence = 1;

            //endregion
        }
        lastTimeMills = currentTimeMillis;
        // 最终的ID
        Long resultId = ((currentTimeMillis - timeStampStart) << timeStampOffset) |
                (machineRoomId << machineRoomOffset) | (machineId << machineIdOffset) | sequence;
        return resultId;
    }

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
