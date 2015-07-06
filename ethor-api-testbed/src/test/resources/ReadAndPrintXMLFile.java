import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ReadAndPrintXMLFile {

	public static void main(String argv[]) {
		try {
			
			//FileOutputStream fileOut = new FileOutputStream("D:\\partime\\efutures\\ethorTest\\ethor-api-testbed\\src\\test\\resources\\MenuItemTestData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(ClassLoader.getSystemResourceAsStream("MenuItemTestData.xlsx"));
			XSSFSheet worksheet = (XSSFSheet) (new SXSSFWorkbook(workbook).getXSSFWorkbook().getSheet("BasicMenuItemDetails"));
			XSSFSheet contentsSheet = (XSSFSheet) (new SXSSFWorkbook(workbook).getXSSFWorkbook().getSheet("MenuItemContents"));

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(
					"D:\\partime\\efutures\\ethorTest\\ethor-api-testbed\\src\\test\\resources\\test.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList menuItems = doc.getElementsByTagName("menuItem");
			int totalPersons = menuItems.getLength();
			System.out.println("Total no of people : " + totalPersons);
			
			XSSFRow headerRow = worksheet.createRow(0);
			headerRow.createCell(1).setCellValue("[ChainId] ChainName");
			headerRow.createCell(2).setCellValue("[ResturantId] ResturantName");
			headerRow.createCell(3).setCellValue("[CategoryId] CategoryName");
			headerRow.createCell(4).setCellValue("[MenuItemId] MenuItemName");
			headerRow.createCell(5).setCellValue("Description");
			headerRow.createCell(6).setCellValue("ImageName");
			headerRow.createCell(7).setCellValue("Sizes");
			headerRow.createCell(8).setCellValue("MinContents");
			headerRow.createCell(9).setCellValue("MaxContents");
			int menuItemCount = 1;
			int contentCount = 1;
			for (int s = 0; s < menuItems.getLength(); s++) {
				


				Node menuItemNode = menuItems.item(s);
				if (menuItemNode.getNodeType() == Node.ELEMENT_NODE) {
					
					XSSFRow menuItemRow = worksheet.createRow(menuItemCount);
					
					Element eElement = (Element) menuItemNode;
					
					XSSFCell menuItemRefIdCell = menuItemRow.createCell(0);
					menuItemRefIdCell.setCellValue("TestMenuItem-" + menuItemCount);
					
					XSSFCell chainIdCell = menuItemRow.createCell(1);
					chainIdCell.setCellValue("[garlicjim] Garlic Jim's"); 
							
					XSSFCell resturantCell = menuItemRow.createCell(2);
					resturantCell.setCellValue("[MjQ=] 2128 N. Glenoaks Blvd #103");
					
					XSSFCell categoryCell = menuItemRow.createCell(3);
					categoryCell.setCellValue("[MjM0MA==] Pizza");

					XSSFCell cellB1 = menuItemRow.createCell(4);
					cellB1.setCellValue("["+getTagValue("id", eElement)+"] " + getTagValue("name", eElement));
					
					XSSFCell descriptionCell = menuItemRow.createCell(5);
					descriptionCell.setCellValue(getTagValue("description", eElement));
					
					XSSFCell imageCell = menuItemRow.createCell(6);
					imageCell.setCellValue(getTagValue("image", eElement));
					// System.out.println("First Name : " + getTagValue("sizes",
					// eElement));
					
					

					NodeList sizes = eElement.getElementsByTagName("sizes");
					StringBuilder sizeString = new StringBuilder();
					for (int i = 0; i < sizes.getLength(); i++) {
						Node menuItemSize = sizes.item(i);
						if (menuItemSize.getNodeType() == Node.ELEMENT_NODE) {
							Element sizeElement = (Element) menuItemSize;
							sizeString.append(getTagValue("name", sizeElement));
							sizeString.append(" | ");
						}
					}
					
					XSSFCell sizesCell = menuItemRow.createCell(7);
					sizesCell.setCellValue(sizeString.toString());
					
					NodeList groupContents = eElement.getElementsByTagName("contentGroups");
					for (int i = 0; i < groupContents.getLength(); i++) {
						Node menuGroupContent = groupContents.item(i);
						if (menuGroupContent.getNodeType() == Node.ELEMENT_NODE) {
							Element groupContentElement = (Element) menuGroupContent;
							//System.out.println("maxContents : " + getTagValue("maxContents", groupContentElement));
							menuItemRow.createCell(8).setCellValue(getTagValue("minContents", groupContentElement));
							menuItemRow.createCell(9).setCellValue(getTagValue("maxContents", groupContentElement));
							
							
							NodeList contents = eElement.getElementsByTagName("contents");
							XSSFRow contentHeaderRaw = contentsSheet.createRow(0);
							contentHeaderRaw.createCell(1).setCellValue("ContentName");
							contentHeaderRaw.createCell(2).setCellValue("PortionSelections");
							contentHeaderRaw.createCell(3).setCellValue("AmountSelections");
							
							
							
							for (int j = 0; j < contents.getLength(); j++) {
								Node menuContent = contents.item(j);
								if (menuContent.getNodeType() == Node.ELEMENT_NODE) {
									XSSFRow contentRaw = contentsSheet.createRow(contentCount++);
									contentRaw.createCell(0).setCellValue("TestMenuItem-" + menuItemCount);
									Element contentElement = (Element) menuContent;
									contentRaw.createCell(1).setCellValue(getTagValue("name", contentElement));
									
									NodeList portionSelections = contentElement.getElementsByTagName("portionSelections");
									StringBuilder portionString = new StringBuilder();
									for (int x = 0; x < portionSelections.getLength(); x++) {
										Node portionSelection = portionSelections.item(x);
										if (portionSelection.getNodeType() == Node.ELEMENT_NODE) {
											Element portionElement = (Element) portionSelection;
											portionString.append(getTagValue("name", portionElement));
											portionString.append(" | ");
										}
									}
									contentRaw.createCell(2).setCellValue(portionString.toString());
									
									NodeList amountSelections = contentElement.getElementsByTagName("amountSelections");
									StringBuilder amountString = new StringBuilder();
									for (int x = 0; x < amountSelections.getLength(); x++) {
										Node amountSelection = amountSelections.item(x);
										if (amountSelection.getNodeType() == Node.ELEMENT_NODE) {
											Element amountElement = (Element) amountSelection;
											amountString.append(getTagValue("name", amountElement));
											amountString.append(" | ");
										}
									}
									contentRaw.createCell(3).setCellValue(amountString.toString());
								}
							}
							
						}
					}
					
					

				}// end of if clause

				menuItemCount++;
			}// end of for loop with s var
			
			FileOutputStream fileOut = new FileOutputStream("D:\\partime\\efutures\\ethorTest\\ethor-api-testbed\\src\\test\\resources\\MenuItemTestData.xlsx");
			workbook.write(fileOut);
		    fileOut.close();


		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
		// System.exit (0);

	}// end of main

	private static String getTagValue(String sTag, Element eElement) {
		NodeList elementsByTagName = eElement.getElementsByTagName(sTag);
		if (elementsByTagName != null && elementsByTagName.item(0) != null) {
			NodeList nlList = elementsByTagName.item(0).getChildNodes();

			Node nValue = (Node) nlList.item(0);

			return nValue.getNodeValue();
		}
		return null;
	}

}