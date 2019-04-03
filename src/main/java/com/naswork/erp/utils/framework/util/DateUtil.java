package com.naswork.erp.utils.framework.util;

import com.naswork.erp.utils.framework.base.BaseModel;
import com.naswork.erp.utils.framework.enums.DateRangeType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Program: DateUtil
 * @Description:
 * @Author: White
 * @DateTime: 2019-03-11 10:09:42
 **/

public class DateUtil {

    private static final String SDF_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String SDF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final String SDF_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public DateUtil() {
    }

    public static final String getYyyyMmDd(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYms = new SimpleDateFormat("yyyy-MM-dd");
            return sdfYms.format(date);
        }
    }

    public static final String getYyyyMmDd(Long date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYms = new SimpleDateFormat("yyyy-MM-dd");
            return sdfYms.format(date);
        }
    }

    public static final String getYyyyMmDdHhMmSs(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYmdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdfYmdhms.format(date);
        }
    }

    public static final String getYyyyMmDdHhMmSs(Long date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYmdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdfYmdhms.format(date);
        }
    }

    public static final String getYyyyMmDdHhMmSsSss(Date date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYmdhmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdfYmdhmss.format(date);
        }
    }

    public static final String getYyyyMmDdHhMmSsSss(Long date) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat sdfYmdhmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdfYmdhmss.format(date);
        }
    }

    public static final Date getDate(String dateStr) {
        if (dateStr == null) {
            return null;
        } else {
            if (dateStr.length() == "yyyy-MM-dd".length()) {
                dateStr = dateStr + " 00:00:00";
            }

            if (dateStr.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                SimpleDateFormat sdfYmdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    Date date = sdfYmdhms.parse(dateStr);
                    return date;
                } catch (ParseException var3) {
                    var3.printStackTrace();
                }
            }

            throw new RuntimeException("Error DateTime string");
        }
    }

    public static void formatDateRange(BaseModel dto) {
        DateRangeType type = dto.getDateRangeType();
        if (type != null) {
            Date timeTo = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(getDayBegin());
            if (type == DateRangeType.HOUR) {
                c.add(11, -1);
            }

            if (type == DateRangeType.YESTERDAY) {
                c.add(5, -1);
            }

            if (type == DateRangeType.WEEK) {
                c.add(5, -7);
            }

            if (type == DateRangeType.MONTH) {
                c.add(2, -1);
            }

            if (type == DateRangeType.QUATER) {
                c.add(2, -3);
            }

            if (type == DateRangeType.YEAR) {
                c.add(1, -1);
            }

            Date timeFrom = c.getTime();
            dto.setTimeFrom(timeFrom);
            dto.setTimeTo(timeTo);
        }
    }

    public static Date getDayBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }
}
