package nju.software.demo.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil<T>  {
	
	/**
	 * ��datasetת��excel��������������Ĭ��Header
	 * @param dataset
	 * @param out
	 */
	public void exportExcel(Collection<T> dataset, OutputStream out) {
		exportExcel("����POI����EXCEL�ĵ�", null, dataset, out, "yyyy-MM-dd",new String[]{});
	}

	/**
	 * ָ��Header���ݣ���datasetת��excel������������
	 * @param headers
	 * @param dataset
	 * @param out
	 */
	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out,String[] ignoreStrings) {
		exportExcel("����POI����EXCEL�ĵ�", headers, dataset, out, "yyyy-MM-dd",ignoreStrings);
	}

	/**
	 * 
	 * @param headers
	 * @param dataset
	 * @param out
	 * @param pattern
	 */
	public void exportExcel(String[] headers, Collection<T> dataset,
			OutputStream out, String pattern) {
		exportExcel("����POI����EXCEL�ĵ�", headers, dataset, out, pattern,new String[]{});
	}

	/**
	 * ����һ��ͨ�õķ�����������JAVA�ķ�����ƣ����Խ�������JAVA�����в��ҷ���һ��������������EXCEL ����ʽ�����ָ��IO�豸��
	 * 
	 * @param title
	 *            ��������
	 * @param headers
	 *            ���������������
	 * @param dataset
	 *            ��Ҫ��ʾ�����ݼ���,������һ��Ҫ���÷���javabean������Ķ��󡣴˷���֧�ֵ�
	 *            javabean���Ե����������л����������ͼ�String,Date,byte[](ͼƬ����)
	 * @param out
	 *            ������豸�����������󣬿��Խ�EXCEL�ĵ������������ļ�����������
	 * @param pattern
	 *            �����ʱ�����ݣ��趨�����ʽ��Ĭ��Ϊ"yyy-MM-dd"
	 */
	@SuppressWarnings("unchecked")
    public void exportExcel(String title, String[] headers,
            Collection<T> dataset, OutputStream out, String pattern, String[] ignoStrings) {
		
		if(StringUtil.isBlank(pattern)){
			pattern = "yyyy-MM-dd";
		}
		
        //����һ��������
        HSSFWorkbook workbook = new HSSFWorkbook();
        
        //����һ�����
        HSSFSheet sheet = workbook.createSheet(title);
        
        //���ñ��Ĭ���п��Ϊ15���ֽ�
        sheet.setDefaultColumnWidth(15);
        
        //����һ����ʽ
        HSSFCellStyle style = workbook.createCellStyle();
        
        //������Щ��ʽ
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        //����һ������
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        
        //������Ӧ�õ���ǰ����ʽ
        style.setFont(font);
        
        //���ɲ�������һ����ʽ
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // ������һ������
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // ������Ӧ�õ���ǰ����ʽ
        style2.setFont(font2);

        // ����һ����ͼ�Ķ���������
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // ����ע�͵Ĵ�С��λ��,����ĵ�
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,0, 0, 0, (short) 4, 2, (short) 6, 5));
        // ����ע������
        comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
        // ����ע�����ߣ�������ƶ�����Ԫ�����ǿ�����״̬���п���������.
        comment.setAuthor("leno");

        // ������������
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // �����������ݣ�����������
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // ���÷��䣬����javabean���Ե��Ⱥ�˳�򣬶�̬����getXxx()�����õ�����ֵ
            Field[] fields = t.getClass().getDeclaredFields();
            int cellindex=0;
            for (int i = 0; i < fields.length; i++) {
            	Field field = fields[i];
                String fieldName = field.getName();
               if(!Arrays.asList(ignoStrings).contains(fieldName)){
//                HSSFCell cell = row.createCell(i);
            	   HSSFCell cell = row.createCell(cellindex);
            	   cellindex++;
                cell.setCellStyle(style2);
             
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    @SuppressWarnings("rawtypes")
					Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,
                            new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // �ж�ֵ�����ͺ����ǿ������ת��
                    String textValue = null;
                    if (value!=null) {
						
					 
                    if (value instanceof Boolean) {
                        boolean bValue = (Boolean) value;
                        textValue = "1";
                        if (!bValue) {
                            textValue = "0";
                        }
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // ��ͼƬʱ�������и�Ϊ60px;
                        row.setHeightInPoints(60);
                        // ����ͼƬ�����п��Ϊ80px,ע�����ﵥλ��һ������
                        sheet.setColumnWidth(i, (int)(35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // �����������Ͷ������ַ����򵥴���
                        textValue = value.toString();
                    }
                    // �������ͼƬ���ݣ�������������ʽ�ж�textValue�Ƿ�ȫ�����������
                    if (textValue != null) {
                    	Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot;");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // �����ֵ���double����
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLUE.index);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // ������Դ
                }
            }
            }

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

	public static void main(String[] args) {
		//����
	}
}
