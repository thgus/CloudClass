package aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.RebootInstancesResult;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;

import java.util.Scanner;
import java.util.List;
import java.util.Scanner;


public class awsTest {
/*
* Cloud Computing, Data Computing Laboratory
* Department of Computer Science
* Chungbuk National University
*/

static AmazonEC2	ec2;

private static void init() throws Exception {
	
	/*
	* The ProfileCredentialsProvider will return your [default]
	* credential profile by reading from the credentials file located at
	* (~/.aws/credentials).
	*/
	
	ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
	try {
		credentialsProvider.getCredentials();
	} catch (Exception e) {
		throw new AmazonClientException(
		"Cannot load the credentials from the credential profiles file. " +
		"Please make sure that your credentials file is at the correct " +
		"location (~/.aws/credentials), and is in valid format.",
		e);
	}
	ec2 = AmazonEC2ClientBuilder.standard()
		.withCredentials(credentialsProvider)
		.withRegion("us-east-1") /* check the region at AWS console */
		.build();
}

public static void main(String[] args) throws Exception {
	init();
	
	Scanner menu = new Scanner(System.in);
	Scanner id_string = new Scanner(System.in);
	int number = 0;
	String id;
	
	while(true)
	{
		System.out.println("								");
		System.out.println("								");
		System.out.println("------------------------------------------------------------");
		System.out.println("	Amazon AWS Control Panel using SDK			");
		System.out.println("								");
		System.out.println(" Cloud Computing, Computer Science Department		");
		System.out.println("				at Chungbuk National University ");
		System.out.println("------------------------------------------------------------");
		System.out.println(" 1. list instance		2. available zones		");
		System.out.println(" 3. start instance		4. available regions		");
		System.out.println(" 5. stop instance		6. create instance		");
		System.out.println(" 7. reboot instance		8. list images			");
		System.out.println(" 99. quit							");
		System.out.println("------------------------------------------------------------");
	
		System.out.print("Enter an integer: ");
		number=menu.nextInt();
		
		switch(number) {
			case 1:
				listInstances();
				break;
			case 2:
				availablezones();
				break;
			case 3:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				startInstance(id);
				break;
			case 4:
				availableregions();
				break;
			case 5:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				stopinstance(id);
				break;
			case 6:
				System.out.print("Enter img_id: ");
				id=id_string.nextLine();
				createinstance(id);
				break;
			case 7:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				rebootinstance(id);
				break;
			case 8:
				listimages();
				break;
			case 99:
				quitmenu();
				break;
		}
	}
}

//menu1
public static void listInstances(){
	System.out.println("Listing instances....");
	boolean done = false;
	DescribeInstancesRequest request = new DescribeInstancesRequest();
	while(!done) {
		DescribeInstancesResult response = ec2.describeInstances(request);
		for(Reservation reservation : response.getReservations()) {
			for(Instance instance : reservation.getInstances()) {
				System.out.printf(
					"[id] %s, " +
					"[AMI] %s, " +
					"[type] %s, " +
					"[state] %10s, " +
					"[monitoring state] %s",
					instance.getInstanceId(),
					instance.getImageId(),
					instance.getInstanceType(),
					instance.getState().getName(),
					instance.getMonitoring().getState());
			}
			System.out.println();
		}
		request.setNextToken(response.getNextToken());
		if(response.getNextToken() == null) {
			done = true;
		}
	}
}

//menu2
public static void availablezones(){
}

//menu3
public static void startInstance(String instance_id){
	StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(instance_id);
	try{	//instance실행
		ec2.startInstances(request);
		System.out.printf("instance_id :"+instance_id+" is successfully start");
	}catch (Exception e){	//실행을 못하면 ex) instance_id를 잘못 입력함.
		 System.out.printf("instance_id("+instance_id+")start is failed");
	}
}

//menu4
public static void availableregions(){
}

//menu5
public static void stopinstance(String instance_id){
	StopInstancesRequest request = new StopInstancesRequest()
            .withInstanceIds(instance_id);
	try{	//instance실행
        ec2.stopInstances(request);
	  System.out.printf("instance_id :"+instance_id+" is successfully stop");
	}catch (Exception e){	//실행을 못하면 ex) instance_id를 잘못 입력함.
		 System.out.printf("instance_id("+instance_id+")stop is failed");
	}
}

//menu6
public static void createinstance(String ami_id ){
}

//menu7
public static void rebootinstance(String instance_id){

}

//menu8
public static void listimages(){
}

//menu99
public static void quitmenu(){
}




}
