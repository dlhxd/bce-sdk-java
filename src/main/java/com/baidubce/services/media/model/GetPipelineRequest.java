/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
 
package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetPipelineRequest extends AbstractBceRequest {

    private String pipelineName = null;

    /**
     **/
    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
    }

    public GetPipelineRequest withPipelineName(String pipelineName) {
        checkStringNotEmpty(pipelineName, "The parameter pipelineName should NOT be null or empty string.");
        this.pipelineName = pipelineName;
        return this;
    }

    public GetPipelineRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetPipelineRequest {\n");

        sb.append("    PipelineName: ").append(pipelineName).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
