# EstModel REST API

 Method | Path  | Type             | Request                                                           | Response
:------ |:----- |:---------------- |:----------------------------------------------------------------- |:-------------------------------------------------------------------------
 POST   | /     | application/json | [Catchment](src/main/java/ee/klab/water/web/model/Catchment.java) | [EstModel](src/main/java/ee/klab/water/web/model/EstModel.java)
 POST   | /lake | application/json | [Lake](src/main/java/ee/klab/water/web/model/Lake.java)           | [EstModel.Lake](src/main/java/ee/klab/water/web/model/EstModel.java#L142)