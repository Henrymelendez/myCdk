package com.myorg;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import software.amazon.awscdk.*;
import software.amazon.awscdk.services.s3.*;
import software.amazon.awscdk.services.ses.actions.S3;
import software.constructs.Construct;
import software.amazon.awscdk.services.sns.Topic;
import software.amazon.awscdk.services.sns.subscriptions.SqsSubscription;
import software.amazon.awscdk.services.sqs.Queue;

public class CdkWorkshopStack extends Stack {
    public CdkWorkshopStack(final Construct parent, final String id) {
        this(parent, id, null);
    }

    public CdkWorkshopStack(final Construct parent, final String id, final StackProps props) {
        super(parent, id, props);

         new Bucket(this, "myBucketId", new BucketProps.Builder().
                 versioned(false)
                 .encryption(BucketEncryption.S3_MANAGED)
                 .blockPublicAccess(BlockPublicAccess.BLOCK_ALL)
                 .build()
                 );

        Bucket myBucket = new Bucket(this, "myBucket");

        CfnOutput.Builder.create(this, "myBucketOutput").value(myBucket.getBucketName())
                .description("my first cdk bucket")
                .exportName("myBucketOutput")
                .build();

        String output1 = "12345";

//        if(!Token.isUnresolved(output1) && output1.length() < 10){
//            throw new ValueException("Max value must be more than 10");
//        }
    }
}
