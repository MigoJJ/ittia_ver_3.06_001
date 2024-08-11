package je.panse.doro.chartplate;

import java.util.HashMap;
import java.util.Map;

import je.panse.doro.samsara.comm.datetime.Date_current;
import je.panse.doro.soap.cc.EMR_ChangeStringCC;

public class EMR_ChangeString {

    private static final Map<String, String> replacements = new HashMap<>();

    static {
        // Populate the map with replacements
    	replacements.put( ":aa  " , "Astrix 100 mg 1 tab po qd ");
    	replacements.put( ":af  " , "Atrial Fibrillation ");
    	replacements.put( ":afr  " , "Atrial Fibrillation with RVR ");
    	replacements.put( ":afl  " , "Atrial Flutter ");
    	replacements.put( ":afp  " , "AFP elevation ");
    	replacements.put( ":ami  " , "Acute Myocardial Infarction ");
    	replacements.put( ":amis  " , "Acute Myocardial Infarction with stent insertion ");
    	replacements.put( ":anx  " , "Anxiety disorder ");
    	replacements.put( ":ap  " , "Angina Pectoris ");
    	replacements.put( ":apc  " , "APC atrial premature complexes ");
    	replacements.put( ":aps  " , "Angina Pectoris with stent insertion ");
    	replacements.put( ":as  " , "Artherosclerosis Carotid artery ");
    	replacements.put( ":asa  " , "Artherosclerosis Carotid artery and Aorta ");
    	replacements.put( ":at  " , "Atypical Chest pain ");
    	replacements.put( ":bco  " , "s/p Breast Cancer Operation ");
    	replacements.put( ":bcoc  " , "s/p Breast Cancer Operation+ ChemoTx(+) ");
    	replacements.put( ":bcor  " , "s/p Breast Cancer Operation + RT(+) ");
    	replacements.put( ":bcocr  " , "s/p Breast Cancer Operation : ChemoTx(+) + RT(+) ");
    	replacements.put( ":bcy  " , "Breast Cyst ");
    	replacements.put( ":bn  " , "Breast Nodule ");
    	replacements.put( ":bnb  " , "Breast Nodule with biopsy ");
    	replacements.put( ":bph  " , "BPH ");
    	replacements.put( ":call  " , "The patient received a lab results phone call notification from the doctor's office. ");
    	replacements.put( ":ca1  " , "CA19-9 elevation ");
    	replacements.put( ":cat  " , "Cataract(+) ");
    	replacements.put( ":cato  " , "Cataract operation (+) [ ] ");
    	replacements.put( ":c  " , "Hypercholesterolemia ");
    	replacements.put( ":cf  " , "Hypercholwsterolemia F/U ");
    	replacements.put( ":cd  " , "Colonic diverticulum [  ] test ");
    	replacements.put( ":cog  " , "Cognitive Disorder ");
    	replacements.put( ":con  " , "Constipation ");
    	replacements.put( ":cov " , "COVID-19 Antibody/Antigen Kit [+] ");
    	replacements.put( ":covc " , "COVID-19 Antibody/Antigen Kit [+] with complications ");
    	replacements.put( ":covs " , "COVID-19 Antibody/Antigen Kit  [+] without complications ");
    	replacements.put( ":cp  " , "Colonic Polyp ");
    	replacements.put( ":cpm  " , "Colonic Polyps multiple ");
    	replacements.put( ":cps  " , "Colonic Polyp single ");
    	replacements.put( ":d  " , "diabetes mellitus ");
    	replacements.put( ":da  " , "DM with Autonomic Neuropathy ");
    	replacements.put( ":dep  " , "Depression ");
    	replacements.put( ":dia  " , "Diarrhea ");
    	replacements.put( ":dn  " , "DM with Nephropathy ");
    	replacements.put( ":dna  " , "DM with Autonomic Neuropathy ");
    	replacements.put( ":dnm  " , "DM with Nephropathy with microalbuminuria ");
    	replacements.put( ":dnc  " , "DM with Nephropathy with CRF ");
    	replacements.put( ":dnp  " , "DM with Peripheral Neuropathy ");
    	replacements.put( ":do  " , "DM without complications ");
    	replacements.put( ":dr  " , "DM with retinopathy ");
    	replacements.put( ":drm  " , "DM with Retinopathy : Macular edema ");
    	replacements.put( ":drn  " , "DM with Retinopathy : Non-proliferative diabetic retinopathy ");
    	replacements.put( ":drp  " , "DM with Retinopathy : Proliferative diabetic retinopathy ");
    	replacements.put( ":df  " , "Diabetes Mellitus F/U ");
    	replacements.put( ":dys  " , "Dysuria and frequency ");
    	replacements.put( ":epi  " , "Epigastric pain ");
    	replacements.put( ":fctg  " , "HyperTriGlyceridemia F/U ");
    	replacements.put( ":fc  " , "Hypercholesterolemia F/U ");
    	replacements.put( ":fd  " , "Diabetes mellitus F/U ");
    	replacements.put( ":fnti  " , "Non-Thyroidal Illness F/U ");
    	replacements.put( ":fte  " , "Hyperthyroidism F/U ");
    	replacements.put( ":ftep  " , "Hyperthyroidism with Pregnancy [ ] weeks F/U ");
    	replacements.put( ":fto  " , "Hypothyroidism F/U ");
    	replacements.put( ":ftop  " , "Hypothyroidism with Pregnancy [ ] weeks F/U ");
    	replacements.put( ":ft  " , "Hypertension F/U ");
    	replacements.put( ":gcon  " , "Severe Constipation ");
    	replacements.put( ":geg  " , "r/o Erosive Gastritis ");
    	replacements.put( ":ggil  " , "Gilbert's syndrome ");
    	replacements.put( ":ggp  " , "Gastric Polyp ");
    	replacements.put( ":ggr  " , "공단검진 결과상담 ");
    	replacements.put( ":gg  " , "공단검진 ");
    	replacements.put( ":gibs  " , "r/o Irritable Bowel Syndrome ");
    	replacements.put( ":gla  " , "Glaucoma(+) ");
    	replacements.put( ":gcag  " , "Chronic Atrophic Gastritis ");
    	replacements.put( ":gcsg  " , "Chronic Superficial Gastritis ");
    	replacements.put( ":got  " , "GOT/GPT/GGT elevation ");
    	replacements.put( ":gre  " , "Reflux esophagitis ");
    	replacements.put( ":grr  " , "GDSRC Result Consultation ");
    	replacements.put( ":gr  " , "GDS RC ");
    	replacements.put( ":gou  " , "Gout ");
    	replacements.put( ":hav  " , "s/p Hepatitis A infection ");
    	replacements.put( ":hea  " , "Headache ");
    	replacements.put( ":hbv  " , "HBsAg(+) Carrier ");
    	replacements.put( ":hcv  " , "Hepatitis C virus (HCV) chronic infection ");
    	replacements.put( ":hcvp  " , "HCV-Ab(Positive) --> PCR(Negative) confirmed ");
    	replacements.put( ":hc  " , "Hepatic Cyst ");
    	replacements.put( ":hf  " , "Fatty Liver ");
    	replacements.put( ":hfmi  " , "Mild Fatty Liver ");
    	replacements.put( ":hfmo  " , "Moderate Fatty Liver ");
    	replacements.put( ":hfse  " , "Severe Fatty Liver ");
    	replacements.put( ":hh  " , "Hepatic Hemangioma ");
    	replacements.put( ":hhn  " , "Hepatic higher echoic nodule ");
    	replacements.put( ":hiv  " , "HIVD : herniated intervertebral disc ");
    	replacements.put( ":hn  " , "Hepatic Nodule ");
    	replacements.put( ":ida  " , "Iron Deficiency Anemia ");
    	replacements.put( ":ind  " , "Epigastric pain and Indigestion ");
    	replacements.put( ":ins  " , "Insomnia ");
    	replacements.put( ":jj  " , "Migo JJ ");
    	replacements.put( ":leu  " , "Leukocytopenia ");
    	replacements.put( ":migo  " , "DR. Koh Jae Joon ");
    	replacements.put( ":nti  " , "Non-Thyroidal Illness ");
    	replacements.put( ":ntm  " , "NTM : Nontuberculous Mycobacterial Pulmonary Disease ");
    	replacements.put( ":oa  " , "s/p Appendectomy ");
    	replacements.put( ":oca  " , "Cataract OP(+) ");
    	replacements.put( ":oco  " , "s/p Colon cancer op(+) ");
    	replacements.put( ":ogc  " , "s/p Gastric cancer cancer op(+) ");
    	replacements.put( ":oh  " , "s/p TAH : Total Abdominal Hysterectomy ");
    	replacements.put( ":oho  " , "s/p TAH with BSO ");
    	replacements.put( ":opr  " , "Prostate cancer operation(+) ");
    	replacements.put( ":os  " , "Osteoporosis ");
    	replacements.put( ":ospe  " , "Osteopenia ");
    	replacements.put( ":ot  " , "Papillary Thyroid Cancer OP(+) with Hypothyroidism ");
    	replacements.put( ":pvc  " , "PVC Premature Ventricular Contractions ");
    	replacements.put( ":pa  " , "s/p Bronchial Asthma ");
    	replacements.put( ":pc  " , "Chronic Cough ");
    	replacements.put( ":pg  " , "Gestational Diabetes Mellitus ");
    	replacements.put( ":pf  " , "Pneumonia ");
    	replacements.put( ":pn  " , "s/p Pulmonary Nodule ");
    	replacements.put( ":pp  " , "Pneumonia ");
    	replacements.put( ":pt  " , "s/p Pulmonary Tuberculosis ");
    	replacements.put( ":rgh  " , "gross hematuria ");
    	replacements.put( ":rih  " , "isolated hematuria ");
    	replacements.put( ":rip  " , "isolated proteinuria ");
    	replacements.put( ":rc  " , "Renal Cyst ");
    	replacements.put( ":rn  " , "Renal Nodule ");
    	replacements.put( ":rr  " , "Other clinic RC and Lab result consultation ");
    	replacements.put( ":rs  " , "Renal Stone ");
    	replacements.put( ":rse  " , "Renal Stone s/p ESWL ");
    	replacements.put( ":sos  " , "Severe Osteoporosis ");
    	replacements.put( ":SxTx  " , "Symptomatic treatment and supportive care ");
    	replacements.put( ":teg  " , "Hyperthyroidism : Greaves' disease ");
    	replacements.put( ":te  " , "Hyperthyroidism ");
    	replacements.put( ":tef  " , "Hyperthyroidism F/U ");
    	replacements.put( ":tf  " , "Hypertension F/U ");
    	replacements.put( ":t  " , "Hypertension ");
    	replacements.put( ":tec  " , "C/W Subacute Thyroiditis ");
    	replacements.put( ":toh  " , "Hypothyroidism : Hashimoto's thyroditis ");
    	replacements.put( ":tn  " , "Thyroid nodule ");
    	replacements.put( ":top  " , "Hypothyroidism with Pregnancy [     ] weeks ");
    	replacements.put( ":to  " , "Hypothyroidism ");
    	replacements.put( ":tof  " , "Hyperthyroidism F/U ");
    	replacements.put( ":ts  " , "C/W Subacute Thyroiditis ");
    	replacements.put( ":tco  " , "Papillary Thyroid Cancer OP(+) Hypothyroidism ");
    	replacements.put( ":tcor  " , "Papillary Thyroid Cancer OP(+) RAI Tx(+) Hypothyroidism ");
    	replacements.put( ":uri  " , "Upper Respiratory Infection ");
    	replacements.put( ":uti  " , "Urinary Tract Infection ");
    	replacements.put( ":ver  " , "Vertigo ");
    	replacements.put( ":wei  " , "Weight loss [ ] kg ");
    	replacements.put( ":weig  " , "Weight gain [ ] kg ");
    	replacements.put( ":ww  " , "with medication. ");
    	replacements.put( ":wq  " , "without medication. ");
    	replacements.put( ":tegf " , "Hyperthyroidism Greaves' disease F/U ");


    }

    public static String EMR_ChangeString(String lines) {
        // Handle special abbreviations
        if (lines.contains(":(")) {
            lines = EMR_ChangeStringCC.EMR_ChangeString_abr(lines);
        } else if (lines.contains(":>")) {
            lines = EMR_ChangeStringCC.EMR_ChangeString_Px(lines);
        }

        // Replace current date placeholder
        String cdate = Date_current.main("d");
        lines = lines.replace(":cd ", cdate);

        // Perform bulk replacements from the map
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            lines = lines.replace(entry.getKey(), entry.getValue());
        }

        return "  " + lines;
    }
}
