package je.panse.doro.soap.plan;

public class ittiaGDSPlanPanel_2_String {
	    private String retA;
	    private String[] retB;

	    public ittiaGDSPlanPanel_2_String(String retA, String[] retB) {
	        this.retA = retA;
	        this.retB = retB;
	    }

	    public String getRetA() {
	        return retA;
	    }

	    public String[] getRetB() {
	        return retB;
	    }
	    
	    public static ittiaGDSPlanPanel_2_String myMethod(int i) {
	        String retA = "123";
	        String[] retB ={"",""};
	        switch (i) {
		        case 1:
		        	retA = "Synthyorid";
		            retB = new String[]{"Synthyorid [ 25 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 37.5 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 50 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 75 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 100 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 112 ] ug 1 tab p.o. q.d.",
		            		"Synthyorid [ 150 ] ug 1 tab p.o. q.d.",
		            };
		            break;
		        case 2:
		        	retA = "Synthyroxine";
		            retB = new String[]{"Synthyroxine [ 25 ] ug 1 tab p.o. q.d.",
		                    "Synthyroxine [ 37.5 ] ug 1 tab p.o. q.d.",
		                    "Synthyroxine [ 50 ] ug 1 tab p.o. q.d.",
		                    "Synthyroxine [ 75 ] ug 1 tab p.o. q.d.",
		                    "Synthyroxine [ 100 ] ug 1 tab p.o. q.d.",
		                    "Synthyroxine [ 150 ] ug 1 tab p.o. q.d."
		                    };		            
	            	break;
		        case 3:
		        	retA = "Antiroid";
		            retB = new String[]{"Antiroid [ 50 ] mg 1 tab p.o. q.d.",
		            		"Antiroid [ 50 ] mg 1 tab p.o. b.i.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. q.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. b.i.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. t.i.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. t.i.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. t.i.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. b.i.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. b.i.d.",
		            		"Antiroid [ 50 ] mg 2 tab p.o. q.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. q.d.",
		            		
		            		"Methimazole [ 5 ] mg 1 tab p.o. q.d.",
		            		"Methimazole [ 5 ] mg 1 tab p.o. b.i.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. q.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. b.i.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. t.i.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. t.i.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. t.i.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. b.i.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. b.i.d.",
		            		"Methimazole [ 5 ] mg 2 tab p.o. q.d.\n"
		            		+ "   Indenol [ 10 ] mg 1 tab p.o. q.d.",

		            		"Camen [ 5 ] mg 1 tab p.o. q.d.",
		            		"Camen [ 5 ] mg 1 tab p.o. b.i.d.",
		            		"Camen [ 5 ] mg 2 tab p.o. q.d.",
		            		"Camen [ 5 ] mg 2 tab p.o. b.i.d.",
		            		"Camen [ 5 ] mg 2 tab p.o. t.i.d.",
		            		"Camen [ 10 ] mg 1 tab p.o. q.d.",
		            		"Camen [ 10 ] mg 1 tab p.o. b.i.d.",
		            		"Camen [ 10 ] mg 2 tab p.o. q.d.",
		            		"Camen [ 10 ] mg 2 tab p.o. b.i.d.",
		            		"Camen [ 10 ] mg 2 tab p.o. t.i.d.",
		            		"Camen [ 20 ] mg 1 tab p.o. q.d."

		            };		            
	            	break;
		        case 4:
		        	retA = "Insulin";
		            retB = new String[]{"Basal or Long Acting Insulins-----\n",
		            		 "Lantus (Insulin Glargine) [    ]\n",
		            		 "Levemir (Insulin Detemir) [    ]\n",
		            		 "Toujeo (Insulin Glargine) [    ]\n",
		            		 "Tresiba (Insulin Degludec) [    ]\n",
		            		 "Basaglar (Insulin Glargine) [    ]\n",
		            		 "\n",
		            		 "Bolus or Fast Acting Insulins-----\n",
		            		 "NovoRapid/Novolog (Insulin Aspart) [    ]n",
		            		 "Humalog (Insulin Lispro) [    ]\n",
		            		 "Apidra (Insulin Glulisine) [    ]\n,",
		            		 "\n",
		            		 "Traditional Human Insulins-----\n",
		            		 "Novolin/Actrapid/Insulatard [    ]\n",
		            		 "Humulin [    ]\n",
		            		 "Insuman [    ]\n",
		            		 "\n",
		            		 "Biosimilar Insulins-----\n",
		            		 "Insulin Glargine Biosimilars [    ]\n",
		            		 "Human Insulin Biosimilars [    ]\n"
		            };		            
	            	break;
		        case 5:
		        	retA = "Plan Diabetes F/U";
		            retB = new String[]{
		            		"Plan to FPG, HbA1C, Lipid profile\n"
				            		 + "\tserum creatinine, eGFR, +A/C ratio)\n"
				            		 + "\tLFT, Electrolyte panel, CBC), Lp(a), ApoB\n"
				            		 + "\tVitamin D level",
				            		
		            		
		            		"Plan to FPG, HbA1C, Lipid profile\n"
		            		 + "\tKidney function tests (serum creatinine\n"
		            		 + "    estimated glomerular filtration rate (eGFR)\n"
		            		 + "    urine albumin-to-creatinine ratio)\n"
		            		 + "\tLiver function tests\n"
		            		 + "\tElectrolyte panel\n"
		            		 + "\tThyroid function tests\n"
		            		 + "\tComplete blood count (CBC)\n"
		            		 + "\tVitamin D level",
		            		 
		            		"plan to Fasting plasma glucose (FPG)\n"
				            	+ "\tGlycated hemoglobin (HbA1C)\n",
				            		 
				            "Obtain to Lipid profile (total, LDL, HDL, triglycerides)\n",
			             
		            };		            
	            	break;
		        case 6:
		        	retA = "Thyroid function test F/U";
		            retB = new String[]{
		            		"Plan to TSH, free thyroxine (T4), T3\n",
				           "Plan to TSH, free thyroxine (T4), T3 + Autoantibodies\n",
				           "Plan to TSH, free thyroxine (T4), T3 + Autoantibodies\n"
				           + "\tp.r.n.> TUS"
		            };		            
	            	break;

		        case 7:
		        	retA = "Obtain Diabetes F/U";
		            retB = new String[]{
		            		"Obtain to FPG, HbA1C, Lipid profile\n"
				            		 + "\tserum creatinine, eGFR, +A/C ratio)\n"
				            		 + "\tLFT, Electrolyte panel, CBC), Lp(a), ApoB\n"
				            		 + "\tVitamin D level",
				            		
		            		
		            		"Obtain to FPG, HbA1C, Lipid profile\n"
		            		 + "\tKidney function tests (serum creatinine\n"
		            		 + "    estimated glomerular filtration rate (eGFR)\n"
		            		 + "    urine albumin-to-creatinine ratio)\n"
		            		 + "\tLiver function tests\n"
		            		 + "\tElectrolyte panel\n"
		            		 + "\tThyroid function tests\n"
		            		 + "\tComplete blood count (CBC)\n"
		            		 + "\tVitamin D level",
		            		 
		            		"Obtain to Fasting plasma glucose (FPG)\n"
				            	+ "\tGlycated hemoglobin (HbA1C)\n",
				            		 
				            "Obtain to Lipid profile (total, LDL, HDL, triglycerides)\n",
		            };	
		            break;
		            
		        case 8:
		        	retA = "Osteoporosis F/U";
		            retB = new String[]{
		            		"Denosumab (Prolia) S.C.",
		            		"본비바주(이반드론산나트륨일수화물) 3mL  Bonviva Inj.\n(3mg as ibandronic acid)3mg을 3개월에 한번씩 15 - 30초 간 정맥주사",
		            		"비타벨라프리필드주사(콜레칼시페롤)  Vitabella Prefilled Inj.\nCholecalciferol 5mg(200,000 i.u. as Vitamin D)",
		            		"비타민D3비오엔주(콜레칼시페롤)  Vitamin D3 B.O.N. INJ.5mg(200,000 i.u. as Vitamin D)",
		            	"Teriparatide (Forteo):a synthetic form of parathyroid hormone",
		            		"알렌드로네이트: 포사맥스+D 70 (Vitamin D3로서 5600 IU)/주1회",
		            };	
		            
		        default:
		            System.out.println("ReEnter the Number !!!");
		            break;
		    }
	        return new ittiaGDSPlanPanel_2_String(retA, retB);
	    }
	    
}
