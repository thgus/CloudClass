package aws;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
//instance 관리
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.RebootInstancesResult;
import com.amazonaws.services.ec2.model.MonitorInstancesRequest;
import com.amazonaws.services.ec2.model.UnmonitorInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
//tag
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TagDescription;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.CreateTagsResult;
import com.amazonaws.services.ec2.model.DescribeTagsRequest;
import com.amazonaws.services.ec2.model.DescribeTagsResult;
import com.amazonaws.services.ec2.model.DeleteTagsRequest;
import com.amazonaws.services.ec2.model.DeleteTagsResult;
//region, zone
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
//image관리
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.CreateImageRequest;
import com.amazonaws.services.ec2.model.CreateImageResult;
import com.amazonaws.services.ec2.model.DeregisterImageRequest;
import com.amazonaws.services.ec2.model.DeregisterImageResult;
//keyPair관리
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairResult;
//Address관리
import com.amazonaws.services.ec2.model.Address;
import com.amazonaws.services.ec2.model.DescribeAddressesResult;
//securityGroup관리
import com.amazonaws.services.ec2.model.SecurityGroup;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupRequest;
import com.amazonaws.services.ec2.model.DeleteSecurityGroupResult;

import java.util.Scanner;
import java.util.List;
import java.util.Scanner;


public class awsTest {

static AmazonEC2	ec2;

private static void init() throws Exception {
	
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
	Scanner name_string = new Scanner(System.in);
	int number = 0;
	String id;
	String name;
	
	while(true)
	{
		System.out.println("										");
		System.out.println("										");
		System.out.println("-------------------------------------------------------------");
		System.out.println("	Amazon AWS Control Panel using SDK				");
		System.out.println("										");
		System.out.println(" Cloud Computing, Computer Science Department			");
		System.out.println("				at Chungbuk National University 	");
		System.out.println("-------------------------------------------------------------");
		System.out.println(" 1. list instance		2. available zones		");
		System.out.println(" 3. start instance		4. available regions		");
		System.out.println(" 5. stop instance		6. create instance		");
		System.out.println(" 7. reboot instance		8. list images			");
		System.out.println(" 9. create keypair		10. Delete Keypair		");
		System.out.println(" 11. monitor instance		12. unmonitor instance		");
		System.out.println(" 13. list Keypair		14. terminate instance		");
		System.out.println(" 15. create image		16. delete image	 		");
		System.out.println(" 17. list address		18. list security group 		");
		System.out.println(" 19. create security group	20. delete security group 	");
		System.out.println(" 21. create tag			22. List tag 			");
		System.out.println(" 23. delete tag						");
		System.out.println(" 					99. quit				");
		System.out.println("-------------------------------------------------------------");
	
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
			case 9:
				System.out.print("Enter KeyName: ");
				name=name_string.nextLine();
				CreateKeyPair(name);
				break;
			case 10:
				System.out.print("Enter KeyName: ");
				name=name_string.nextLine();
				DeleteKeyPair(name);
				break;
			case 11:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				monitorInstance(id);
				break;
			case 12:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				unmonitorInstance(id);
				break;
			case 13:
				DescribeKeyPairs();
				break;	
			case 14:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				terminateInstance(id);
				break;
			case 15:
				System.out.print("Enter instance_id: ");
				id=id_string.nextLine();
				System.out.print("Enter ImageName: ");
				name=name_string.nextLine();
				createImage(id,name);
				break;
			case 16:
				System.out.print("Enter image_id: ");
				id=id_string.nextLine();
				deleteImage(id);
				break;
			case 17:
				describeAddresses();
				break;
			case 18:
				describeSecurityGroups();
				break;
			case 19:
				createSecurityGroup();
				break;
			case 20:
				System.out.print("Enter SecurityGroup_id: ");
				id=id_string.nextLine();
				deleteSecurityGroup(id);
				break;
			case 21:
				System.out.print("Enter TagName: ");
				name=name_string.nextLine();
				System.out.print("Enter resource id: ");
				id=id_string.nextLine();
				createTag(name, id);
				break;
			case 22:
				describeTags();
				break;
			case 23:
				System.out.print("Enter resource id: ");
				id=id_string.nextLine();
				System.out.print("Enter TagName: ");
				name=name_string.nextLine();
				deleteTags(id, name);
				break;
			case 99:
				quitmenu();
				break;
			default:
				System.out.print("try again!\n");
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
	System.out.println("Listing zones....");
	DescribeAvailabilityZonesResult zones_response = ec2.describeAvailabilityZones();

	for(AvailabilityZone zone : zones_response.getAvailabilityZones()) {
    		System.out.printf(
     		   "[id] %s	" +
    			"[region] %s	" +
			"[zone] %s\n",
		      zone.getZoneId(),
			zone.getRegionName(),		      
			zone.getZoneName());
	}

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
	System.out.println("Listing regions....");
	DescribeRegionsResult regions_response = ec2.describeRegions();
	for(Region region : regions_response.getRegions()) {
	    System.out.printf(
	        "[region] %s	" +
	        "[endpoint] %s\n",
	        region.getRegionName(),
	        region.getEndpoint()
		);
	}
}

//menu5
public static void stopinstance(String instance_id){
	StopInstancesRequest request = new StopInstancesRequest()
            .withInstanceIds(instance_id);
	try{	//instance중지
        ec2.stopInstances(request);
	  System.out.printf("instance_id :"+instance_id+" is successfully stop");
	}catch (Exception e){	
		 System.out.printf("instance_id("+instance_id+")stop is failed");
	}
}

//menu6
public static void createinstance(String ami_id){

String reservation_id=null;
RunInstancesResult run_response;
RunInstancesRequest run_request;

try{
run_request = new RunInstancesRequest()
    .withImageId(ami_id)
    .withInstanceType(InstanceType.T2Micro)
    .withMaxCount(1)
    .withMinCount(1);

run_response = ec2.runInstances(run_request);
reservation_id = run_response.getReservation().getInstances().get(0).getInstanceId();

System.out.printf("Successfully started EC2 instance %s based on AMI %s\n",reservation_id, ami_id);
}catch (Exception e){
	System.out.printf("createInstance is failed\n");
}
}

//menu7
public static void rebootinstance(String instance_id){
	RebootInstancesRequest request = new RebootInstancesRequest().withInstanceIds(instance_id);
	try{	//instance리부트
        ec2.rebootInstances(request);
	  System.out.printf("instance_id :"+instance_id+" is successfully reboot");
	}catch (Exception e){	
		 System.out.printf("instance_id("+instance_id+")reboot is failed");
	}
}

//menu8
public static void listimages(){
	System.out.println("Listing images....");
	
	DescribeImagesRequest request = new DescribeImagesRequest();
	request.withOwners("self");
	DescribeImagesResult response=ec2.describeImages(request);	
	
	try{
		if(response != null){
			for(Image image : response.getImages()){
    				System.out.printf(
     				   "[id] %s	" +
    					"[Location] %s	" +
					"[type] %s	"+
					"[date] %s\n",
				      image.getImageId(),
					image.getImageLocation(),
					image.getImageType(),		      
					image.getCreationDate());
			}
		}else{
			System.out.println("No images found");	
		}
	}catch (Exception e){
		System.out.printf("image listing is failed");
	}
}
//menu9
public static void CreateKeyPair(String name) {
	try{	
	CreateKeyPairRequest request = new CreateKeyPairRequest()
            .withKeyName(name);

        CreateKeyPairResult response = ec2.createKeyPair(request);
        System.out.printf("Successfully created key pair named %s",name);
	}catch(Exception e){
		System.out.printf("create keypair is failed");	
	}
}

//menu10
public static void DeleteKeyPair(String name) {
	Scanner scanner = new Scanner(System.in);
	int opinion;
	try{	
	 System.out.printf("Do you really want to erase it? (yes :1 ,No :2) ");
	opinion=scanner.nextInt();
	if(opinion==1){
	 DeleteKeyPairRequest request = new DeleteKeyPairRequest()
            .withKeyName(name);
        DeleteKeyPairResult response = ec2.deleteKeyPair(request);
        System.out.printf("Successfully deleted key pair named %s",name);
	}
	else{
	  System.out.printf("delet keypair if failed : (name) %s ",name);
	}
	}catch(Exception e){
		System.out.printf("delete keypair is failed : (name) %s", name);	
	}
}

//menu11
public static void monitorInstance(String id){
try{
	MonitorInstancesRequest request = new MonitorInstancesRequest()
                .withInstanceIds(id);
	ec2.monitorInstances(request);
      System.out.printf( "Successfully enabled monitoring for instance %s",id);
}catch(Exception e){
	System.out.printf("monitoring is failed : (id) %s", id);
}
} 

//menu12
public static void unmonitorInstance(String id){
try{
	UnmonitorInstancesRequest request = new UnmonitorInstancesRequest()
            .withInstanceIds(id);
	ec2.unmonitorInstances(request);
	System.out.printf( "Successfully disabled monitoring for instance %s", id);
}catch(Exception e){
	System.out.printf("disable monitor is failed : (id) %s", id);
}
} 

//menu13
public static void DescribeKeyPairs(){
	System.out.println("Listing keyPair....");

	DescribeKeyPairsResult response = ec2.describeKeyPairs();
        for(KeyPairInfo key_pair : response.getKeyPairs()) {
            System.out.printf(
                "[name]	%s " +
                "[fingerprint]	%s\n",
                key_pair.getKeyName(),
                key_pair.getKeyFingerprint());
        }
}

//menu14
public static void terminateInstance(String instance_id) {
	TerminateInstancesRequest request = new TerminateInstancesRequest().withInstanceIds(instance_id);
	try{	//instance 종료
        ec2.terminateInstances(request);
	  System.out.printf("instance_id :"+instance_id+" is successfully terminate");
	}catch (Exception e){	
		 System.out.printf("instance_id("+instance_id+")stop is failed");
	}
}

//menu15
public static void createImage(String instance_id,String name){
	try{	
	CreateImageRequest request = new CreateImageRequest(instance_id, name);

      CreateImageResult response = ec2.createImage(request);
        System.out.printf("Successfully created image [name] %s ",name);
	}catch(Exception e){
		System.out.printf("create image is failed");	
	}
}

//menu 16
public static void deleteImage(String id){
	Scanner scanner = new Scanner(System.in);
	int opinion;
	try{	
		System.out.printf("Do you really want to erase it? (yes :1 ,No :2) ");
		opinion=scanner.nextInt();
		if(opinion==1){
			 DeregisterImageRequest request = new DeregisterImageRequest()
            		.withImageId(id);
        		DeregisterImageResult response = ec2.deregisterImage(request);
        		System.out.printf("Successfully deleted image. id %s",id);
		}
		else{
	  		System.out.printf("delet image is failed : (id) %s ",id);
		}
	}catch(Exception e){
		System.out.printf("delete image is failed : (id) %s", id);	
	}
}

//menu 17
public static void describeAddresses(){
	System.out.println("Listing Addresses....");
	DescribeAddressesResult response = ec2.describeAddresses();

        for(Address address : response.getAddresses()) {
            System.out.printf(
                    "[public IP]	%s, " +
                    "[domain]	%s, " +
                    "[allocation Id]	%s " +
                    "[NIC Id]	%s\n",
                    address.getPublicIp(),
                    address.getDomain(),
                    address.getAllocationId(),
                    address.getNetworkInterfaceId());
        }
}

//menu18
public static void describeSecurityGroups(){
	System.out.println("Listing SecurityGroups....");
	DescribeSecurityGroupsRequest request =new DescribeSecurityGroupsRequest();
	DescribeSecurityGroupsResult response = ec2.describeSecurityGroups(request);
	for(SecurityGroup group : response.getSecurityGroups()) {
            System.out.printf(
                "[id] %s," +
                "[name] %s," +
                "[vpc id] %s " +
                "[description] %s\n",
                group.getGroupId(),
                group.getGroupName(),
                group.getVpcId(),
                group.getDescription());
        }
}

//menu19
public static void createSecurityGroup(){
	Scanner scanner = new Scanner(System.in);
	String name, desc, id;

	System.out.print("Enter group_name: "); name=scanner.nextLine();
	System.out.print("Enter group_VpnId: "); id=scanner.nextLine();
	System.out.print("Enter group_desc: "); desc=scanner.nextLine();

	try{	
        CreateSecurityGroupRequest create_request = new CreateSecurityGroupRequest()
               							 .withGroupName(name)
              							 .withDescription(desc)
      	     							 .withVpcId(id);
        CreateSecurityGroupResult create_response = ec2.createSecurityGroup(create_request);
		System.out.printf("Successfully created security group. (name) %s",name);
	} catch (Exception e){
		System.out.printf("create security group is failed : (name) %s", name);	
	}
}

//menu20
public static void deleteSecurityGroup(String id){
	Scanner scanner = new Scanner(System.in);
	int opinion;
	try{	
		System.out.printf("Do you really want to erase it? (yes :1 ,No :2) ");
		opinion=scanner.nextInt();
		if(opinion==1){
     		   DeleteSecurityGroupRequest request = new DeleteSecurityGroupRequest()
         								   .withGroupId(id);
     		   DeleteSecurityGroupResult response = ec2.deleteSecurityGroup(request);
        		System.out.printf("Successfully deleted security group. id %s",id);
		}
		else{
	  		System.out.printf("deletsecuritygroup is failed : (id) %s ",id);
		}
	}catch(Exception e){
		System.out.printf("delete security group is failed : (id) %s", id);	
	}
}

//menu21
public static void createTag(String name, String id){
	try{
	Tag tag = new Tag()
            .withKey("Name")
            .withValue(name);

        CreateTagsRequest tag_request = new CreateTagsRequest()
            					.withResources(id)
            					.withTags(tag);
        CreateTagsResult tag_response = ec2.createTags(tag_request);

        System.out.printf(
            "Successfully createTag (name) %s ",name);	
	}catch(Exception e){
		System.out.printf("create tag is failed : (name) %s", name);
	}
}

//menu22
public static void describeTags(){
	System.out.println("Listing Tags....");

	DescribeTagsRequest request = new DescribeTagsRequest();
	DescribeTagsResult response = ec2.describeTags(request);

	for(TagDescription tag : response.getTags()) {
            System.out.printf(
                "[id] %s," +
		    "[Key] %s," +
                "[value] %s\n",
	          tag.getResourceId(),
                tag.getKey(),
                tag.getValue());
        }
}	

//menu23
public static void deleteTags(String id, String name){
	Scanner scanner = new Scanner(System.in);
	int opinion;
	try{	
		System.out.printf("Do you really want to erase it? (yes :1 ,No :2) ");
		opinion=scanner.nextInt();
		if(opinion==1){
			Tag tag = new Tag()
            			.withKey("Name")
          			  	.withValue(name);
     		   DeleteTagsRequest request = new DeleteTagsRequest()
         						   .withResources(id)
         						   .withTags(tag);
     		   DeleteTagsResult response = ec2.deleteTags(request);
        		System.out.printf("Successfully deleted tag. id %s",id);
		}
		else{
	  		System.out.printf("delete tag is failed : (id) %s ",id);
		}
	}catch(Exception e){
		System.out.printf("delete tag is failed : (id) %s", id);	
	}
}	

//menu99
public static void quitmenu(){
	System.out.printf("quit\n bye bye :)\n");
	System.exit(0);
}




}
