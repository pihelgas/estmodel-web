# EstModel REST API

 Method | Path  | Type             | Request                                                              | Response                                                                      | Description
:------ |:----- |:---------------- |:-------------------------------------------------------------------- |:----------------------------------------------------------------------------- |:---------------
 POST   | /     | application/json | [EstModel](src/main/java/ee/klab/water/web/model/EstModel.java)      | [EstModel.Estimate](src/main/java/ee/klab/water/web/model/EstModel.java)      | EstModel
 POST   | /lake | application/json | [EstModel.Lake](src/main/java/ee/klab/water/web/model/EstModel.java) | [EstModel.Lake.Estimate](src/main/java/ee/klab/water/web/model/EstModel.java) | EstModel: Lake