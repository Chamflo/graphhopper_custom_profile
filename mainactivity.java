 void loadGraphStorage() {
        logUser("loading graph (" + Constants.VERSION + ") ... ");
        new GHAsyncTask<Void, Void, Path>() {
            protected Path saveDoInBackground(Void... v) throws Exception {
                GraphHopper tmpHopp = new GraphHopper().forMobile();
                tmpHopp.setEncodingManager(EncodingManager.create(new CustomFlagEncoder(4,2,0)));
                        //tmpHopp.setEncodingManager(EncodingManager.create(FlagEncoderFactory.BIKE));
                        //tmpHopp.setEncodingManager(new EncodingManager( "bike" ));
                        //EncodingManager encodingManager = EncodingManager.create(new RacingBikeFlagEncoder(), new MountainBikeFlagEncoder());
                        //tmpHopp.setEncodingManager(encodingManager);
                tmpHopp.load(new File(mapsFolder, currentArea).getAbsolutePath() + "-gh");
                        //tmpHopp.init(new CmdArgs().put("graph.flag_encoders", "custom").put(Parameters.CH.PREPARE + "weightings", "no"));
                        //tmpHopp.importOrLoad();
                log("found graph " + tmpHopp.getGraphHopperStorage().toString() + ", nodes:" + tmpHopp.getGraphHopperStorage().getNodes());
                hopper = tmpHopp;
                return null;
            }

            protected void onPostExecute(Path o) {
                if (hasError()) {
                    logUser("An error happened while creating graph:"
                            + getErrorMessage());
                } else {
                    logUser("Finished loading graph. Long press to define where to start and end the route.");
                }

                finishPrepare();
            }
        }.execute();
    }
