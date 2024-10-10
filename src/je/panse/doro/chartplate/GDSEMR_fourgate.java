package je.panse.doro.chartplate;

import java.io.IOException;		

import je.panse.doro.GDSEMR_frame;
import je.panse.doro.samsara.EMR_east_buttons_obj;
import je.panse.doro.soap.assessment.AssessmentSupport;
import je.panse.doro.soap.cc.CCSupport;
import je.panse.doro.soap.pe.EMR_PE_general;
import je.panse.doro.soap.plan.IttiaGDSPlan;
import je.panse.doro.soap.pmh.EMRPMH;
import je.panse.doro.soap.ros.EMR_ROS;
import je.panse.doro.soap.subjective.EMR_symptom_main;

public class GDSEMR_fourgate extends GDSEMR_frame {

	public GDSEMR_fourgate() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String text) throws IOException {
		System.out.println("Double-clicked on: text >>> " + text);
		if (text.contains("PMH>")) {
			EMRPMH.main(null);
		} else if (text.contains("CC>")) {
			CCSupport.main(null);
		} else if (text.contains("ROS>")) {
				EMR_ROS.main(null);
		} else if (text.contains("S>")) {
			EMR_symptom_main.main(null);
		} else if (text.contains("O>")) {
			EMR_east_buttons_obj.main(text);
		} else if (text.contains("A>")) {
			AssessmentSupport.main(null);
		} else if (text.contains("P>")) {
			IttiaGDSPlan.main(null);
		} else if (text.contains("Physical Exam>")) {
			EMR_PE_general.main(null);
		}
	}
}
