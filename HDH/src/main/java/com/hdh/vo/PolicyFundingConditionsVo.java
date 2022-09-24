package com.hdh.vo;

import lombok.Data;

@Data
public class PolicyFundingConditionsVo {

	/** 정책자금 이름 */
	private String policyFundName                      ;

	/** 산업분류코드 */
	private String industrialClassificationCode        ;

	/** 업력 */
	private String BusinessAbility                     ;

	/** 기업부설연구소/전담부서 */
	private String InstituteCorporateSubsidiaryYn      ;

	/** 벤처인증 */
	private String VentureCertificationYn              ;

	/** 이노비즈인증 */
	private String InobizYn                            ;

	/** 메인비즈인증 */
	private String MainBizYn                           ;

	/** 3년이내특허 */
	private String PatentCaseThreeYearsYn              ;

	/** 소재부품인증 */
	private String MaterialPartCertificationYn         ;

	/** 가족친화인증 */
	private String FamilyFriendlyCertificationYn       ;

	/** 뿌리기업인증 */
	private String RootCompanyCertificationYn          ;

	/** iso9001 */
	private String IsoCertificationYn                  ;

	/** 상시근로자5인유무 */
	private String RegularWorkerFiveYn                 ;

	private String FactoryYn                           ;
	/** 공장유무 */

}
