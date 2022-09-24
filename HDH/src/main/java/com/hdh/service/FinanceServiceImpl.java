package com.hdh.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.IFinanceDao;
import com.hdh.vo.CalculationVo;
import com.hdh.vo.FinanceVo;

@Service
public class FinanceServiceImpl implements iFinanceService {

	@Autowired
	IFinanceDao FinanceDao;

	@Override
	public void insertFinance(FinanceVo Vo) {

		// 1. 입력한 Vo 1단계(당사 계산 이전) 계산

		/** 자산 (유동자산 + 비유동자산 ) */
		Vo.setProperty( Vo.getLiquidAssets() + Vo.getNonCurrentAssets());

		/** 부채 (유동부채 + 비유동부채 ) */
		Vo.setDebt( Vo.getCurrentLiabilities() + Vo.getNonCurrentLiability());

		/** 자기자본 (자본금 + 미처분이익잉여금 ) */
		Vo.setEquityCapital( Vo.getCapital() + Vo.getRetainedEarningsArrears());


		/** 매출액 (제품매출액 + 상품매출엑 + 그외매출액) */
		Vo.setSales(Vo.getProductSales() + Vo.getWareSales() + Vo.getOtherSales());

		/** 매출총이익 (매출액 - 매출원가) */
		Vo.setGrossProfitSales( Vo.getSales() - Vo.getSalesCost());

		/** 영업이익 (매출총이익 - 판매비와관리비) */
		Vo.setOperatingProfit( Vo.getGrossProfitSales() - Vo.getSalesAdministrativeExpenses());

		/** 세후순이익 (세전순이익 - 법인세비용) */
		Vo.setNetIncomeTax(Vo.getNetIncomeBeforeTax() - Vo.getCorporateTaxExpense());

		/** 총차입금 (단기차입금 + 장기차입금 ) */
		Vo.setTotalBorrowing( Vo.getShortTermBorrowings() + Vo.getLongTermLoans());

		/** 총 연구개발비 ( (제조-연구개발비 + 개발비 + 연구개발비 ) ) */
		Vo.setTotalRDExpenses( (Vo.getManufacturingCostRDExpenses() + Vo.getDevelopmentCost() + Vo.getRDExpenses()) );


		// 2. 당사 계산, DB저장
		FinanceDao.insertFinance(Vo);


		CalculationVo CVo = new CalculationVo();
		CVo.setNo(Vo.getNo());
		CVo.setId(Vo.getId());
		CVo.setDate(Vo.getDate());

		/** 자기자본비율          		(자기자본/자산)*100 					*/
		CVo.setEquityCapitalRatio( ((double)Vo.getEquityCapital() / (double)Vo.getProperty()) * 100 );

		/** 유동비율              			(유동자산/유동부채)*100 					*/
		CVo.setFlowRate( ((double)Vo.getLiquidAssets() / (double)Vo.getCurrentLiabilities()) * 100 );

		/** 당좌비율              			(당좌자산/유동부채)*100 					*/
		CVo.setCheckingRatio( ((double)Vo.getPartyProperty() / (double)Vo.getCurrentLiabilities()) * 100 );

		/** 부채비율              			(유동부채+비유동부채)/자기자본*100 			*/
		CVo.setDebtRatio( (Vo.getCurrentLiabilities() + Vo.getEquityCapital()) / Vo.getEquityCapital() * 100 );

		/** 차입금의존도          		(장.단기차입금+회사채)/자산*100 			*/
		// TODO: 회사채 컬럼 확인필요 CVo.setBorrowingDependence( (Vo.getShortTermBorrowings() + Vo.getLongTermLoans() + Vo.getCorporateBonds()) / (double)Vo.getProperty() * 100 );

		/** 단기차입금비율        		(단기차입금/총차입금)*100 				*/
		CVo.setShortTermBorrowingRatio( ((double)Vo.getShortTermBorrowings() / ((double)Vo.getShortTermBorrowings() + Vo.getLongTermLoans())) * 100 );

		/** 현금 및유가증권비율   		(현금+유가증권)/유동부채*100 				*/
		// TODO: 유가증권 컬럼 확인필요 CVo.setCashSecuritiesRatio( (Vo.getCashCashableAssets() + Vo.getSecurities()) / (double)Vo.getCurrentLiabilities() * 100 );

		/** 차입금평균이자율      		(이자비용/((회사채+장.단기차입금)의평균)))*100	*/

		/** 연구개발비율          		(총연구개발비/매출액)*100 					*/
		CVo.setRDRatio( ((double)Vo.getTotalRDExpenses() / (double)Vo.getSales()) * 100 );

		/** 차입금&매출액         		(차입금(평균)/매출액)*100 				*/
		// TODO: 평균 확인필요 Vo.setBorrowingSales();
		/** 금융비용대부채        		(이자비용/부채(평균))*100 				*/
		// TODO: 평균 확인필요 Vo.setFinancialExpensesVsLiabilities();

		/** 이자보상배수          		(영업이익/이자비용)						*/
		CVo.setInterestCompensationMultiple( (double)Vo.getOperatingProfit() / (double)Vo.getInterestExpense() );

		/** 자산이익률            		(당기순이익/자산)*100 					*/
		CVo.setReturnAssets( ((double)Vo.getNetIncomeTax() / (double)Vo.getProperty()) * 100 );

		/** 매출액영업이익율      		(영업이익/매출액)*100 					*/
		CVo.setEarningsRatioSalesOperation( ((double)Vo.getOperatingProfit() / (double)Vo.getSales()) * 100 );


		/** 매출액세전계속사업이익		(세전순이익/매출액)*100율 					*/
		CVo.setContinuedBusinessProfitRatioBeforeSalesTax( ((double)Vo.getNetIncomeBeforeTax() / (double)Vo.getSales()) * 100 );

		/** 매출액순이익률        		(당기순이익/매출액)*100 					*/
		CVo.setNetProfitRatioSales( ((double)Vo.getNetIncomeTax() / (double)Vo.getSales()) * 100 );

		/** 금융비용대매출액비율  		(이자비용/매출액)*100 					*/
		CVo.setFinancialExpenseSalesRatio( ((double)Vo.getInterestExpense() / (double)Vo.getSales()) * 100 );

		/** 이자보상비율          		(영업이익/이자비용)*100					*/
		CVo.setInterestCoverageRatio( ((double)Vo.getOperatingProfit() / (double)Vo.getInterestExpense()) * 100);

		/** 자기자본순이익률      		(당기순이익/자기자본(평균))*100 			*/
		// TODO: 평균 확인필요 CVo.setReturnEquity();
		/** 자본회전률            		(매출액/자본(평균)) 						*/
		// TODO: 평균 확인필요 CVo.setCapitalTurnover();
		/** 매출채권회전률        		(매출액/매출채권(평균)) 					*/
		// TODO: 평균 확인필요 CVo.setTurnoverRateAccountsReceivable();
		/** 재고자산회전률        		(매출액/재고자산(평균)) 					*/
		// TODO: 평균 확인필요 CVo.setInventoryAssetTurnoverRate();
		/** 유형자산회전률        		(매출액/유형자산(평균)) 					*/
		// TODO: 평균 확인필요 CVo.setTurnoverRateTangibleAssets();
		/** 매입채무회전률        		(매출액/매입채무(평균)) 					*/
		// TODO: 평균 확인필요 CVo.setTurnoverRatePurchaseDebt();


		/** 매출액증가율          		(당기매출액/전기매출액)*100-100 			*/
		// CVo.setIncreaseSales();
		/** 순이익증가율          		(당기순이익/전기순이익)*100-100 			*/
		// CVo.setNetIncomeGrowthRate();
		/** 자산증가율            		(당기말자산/전기자산)*100-100 			*/
		// CVo.setAssetGrowthRate();
		/** 자기자본증가율        		(당기말자기자본/전기말자기자본)*100-100 		*/
		// CVo.setGrowthRateEquity();
		/** 유형자산증가율        		(당기말유형자산/전기말유형자산)*100-100 		*/
		// CVo.setPropertyPlantEquipmentGrowthRate();

		CVo.setBusinessLicenseNumber(Vo.getBusinessLicenseNumber());

		FinanceDao.insertCFinance(CVo);


		// 3. 년도별 (전기, 당기) 데이터 조회 이후 당사의 성장성 계산

		LocalDate date = LocalDate.now();
		int year = date.getYear();

	}

}
