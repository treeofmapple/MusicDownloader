package com.github.felipeucelli.javatube;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

class InnerTube{
    private static JSONObject innerTubeContext;
    private static boolean requireJsPlayer;
    private static JSONObject header;
    private static String apiKey;

    /**
     * @Clients:
     *          WEB,
     *          WEB_EMBED,
     *          WEB_MUSIC,
     *          WEB_CREATOR,
     *          WEB_SAFARI,
     *          MWEB,
     *          ANDROID,
     *          ANDROID_VR,
     *          ANDROID_MUSIC,
     *          ANDROID_CREATOR,
     *          ANDROID_TESTSUITE,
     *          ANDROID_PRODUCER,
     *          IOS,
     *          IOS_MUSIC,
     *          IOS_CREATOR,
     *          TV_EMBED,
     *          MEDIA_CONNECT
     * */
    public InnerTube(String client) throws JSONException {
        JSONObject defaultClient = new JSONObject("""
                {
                  "WEB": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "WEB",
                          "osName": "Windows",
                          "osVersion": "10.0",
                          "clientVersion": "2.20240709.01.00",
                          "platform": "DESKTOP"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "1",
                      "X-Youtube-Client-Version": "2.20240709.01.00"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "WEB_EMBED": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "WEB_EMBEDDED_PLAYER",
                          "osName": "Windows",
                          "osVersion": "10.0",
                          "clientVersion": "2.20240530.02.00",
                          "clientScreen": "EMBED"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "56"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "WEB_MUSIC": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "WEB_REMIX",
                          "clientVersion": "1.20240403.01.00"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "67"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "WEB_CREATOR": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "WEB_CREATOR",
                          "clientVersion": "1.20220726.00.00"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "62"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "WEB_SAFARI": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "WEB",
                          "clientVersion": "2.20240726.00.00"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.5 Safari/605.1.15,gzip(gfe)",
                      "X-Youtube-Client-Name": "1"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "MWEB": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "MWEB",
                          "clientVersion": "2.20240726.01.00"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Mobile Safari/537.36",
                      "X-Youtube-Client-Name": "2"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "ANDROID": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID",
                          "clientVersion": "19.29.37",
                          "platform": "MOBILE",
                          "osName": "Android",
                          "osVersion": "14",
                          "androidSdkVersion": "34"
                        }
                      },
                      "params": "2AMB"
                    },
                    "header": {
                      "User-Agent": "com.google.android.youtube/",
                      "X-Youtube-Client-Name": "3"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "ANDROID_VR": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID_VR",
                          "clientVersion": "1.57.29",
                          "deviceMake": "Oculus",
                          "deviceModel": "Quest 3",
                          "osName": "Android",
                          "osVersion": "12L",
                          "androidSdkVersion": "32"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.android.apps.youtube.vr.oculus/1.57.29 (Linux; U; Android 12L; eureka-user Build/SQ3A.220605.009.A1) gzip",
                      "X-Youtube-Client-Name": "28"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "ANDROID_MUSIC": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID_MUSIC",
                          "clientVersion": "7.11.50",
                          "androidSdkVersion": "30",
                          "osName": "Android",
                          "osVersion": "11"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.android.apps.youtube.music/7.11.50 (Linux; U; Android 11) gzip",
                      "X-Youtube-Client-Name": "21"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "ANDROID_CREATOR": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID_CREATOR",
                          "clientVersion": "24.30.100",
                          "androidSdkVersion": "30",
                          "osName": "Android",
                          "osVersion": "11"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.android.apps.youtube.creator/24.30.100 (Linux; U; Android 11) gzip",
                      "X-Youtube-Client-Name": "14"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "ANDROID_TESTSUITE": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID_TESTSUITE",
                          "clientVersion": "1.9",
                          "platform": "MOBILE",
                          "osName": "Android",
                          "osVersion": "14",
                          "androidSdkVersion": "34"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.android.youtube/",
                      "X-Youtube-Client-Name": "30",
                      "X-Youtube-Client-Version": "1.9"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "ANDROID_PRODUCER": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "ANDROID_PRODUCER",
                          "clientVersion": "0.111.1",
                          "androidSdkVersion": "30",
                          "osName": "Android",
                          "osVersion": "11"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.android.apps.youtube.producer/0.111.1 (Linux; U; Android 11) gzip",
                      "X-Youtube-Client-Name": "91"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "IOS": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "IOS",
                          "clientVersion": "19.29.1",
                          "deviceMake": "Apple",
                          "platform": "MOBILE",
                          "osName": "iPhone",
                          "osVersion": "17.5.1.21F90",
                          "deviceModel": "iPhone16,2"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.ios.youtube/19.29.1 (iPhone16,2; U; CPU iOS 17_5_1 like Mac OS X;)",
                      "X-Youtube-Client-Name": "5"
                    },
                    "apiKey": "AIzaSyB-63vPrdThhKuerbB2N_l7Kwwcxj6yUAc",
                    "requireJsPlayer": "false"
                  },
                
                  "IOS_MUSIC": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "IOS_MUSIC",
                          "clientVersion": "7.08.2",
                          "deviceMake": "Apple",
                          "platform": "MOBILE",
                          "osName": "iPhone",
                          "osVersion": "17.5.1.21F90",
                          "deviceModel": "iPhone16,2"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.ios.youtubemusic/7.08.2 (iPhone16,2; U; CPU iOS 17_5_1 like Mac OS X;)",
                      "X-Youtube-Client-Name": "26"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "IOS_CREATOR": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "IOS_CREATOR",
                          "clientVersion": "24.30.100",
                          "deviceMake": "Apple",
                          "deviceModel": "iPhone16,2",
                          "osName": "iPhone",
                          "osVersion": "17.5.1.21F90"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "com.google.ios.ytcreator/24.30.100 (iPhone16,2; U; CPU iOS 17_5_1 like Mac OS X;)",
                      "X-Youtube-Client-Name": "15"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  },
                
                  "TV_EMBED": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "TVHTML5_SIMPLY_EMBEDDED_PLAYER",
                          "clientVersion": "2.0",
                          "clientScreen": "EMBED",
                          "platform": "TV"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "85"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "true"
                  },
                
                  "MEDIA_CONNECT": {
                    "innerTubeContext": {
                      "context": {
                        "client": {
                          "clientName": "MEDIA_CONNECT_FRONTEND",
                          "clientVersion": "0.1"
                        }
                      }
                    },
                    "header": {
                      "User-Agent": "Mozilla/5.0",
                      "X-Youtube-Client-Name": "95"
                    },
                    "apiKey": "AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8",
                    "requireJsPlayer": "false"
                  }
                }
                """);

        innerTubeContext = defaultClient.getJSONObject(client).getJSONObject("innerTubeContext");
        requireJsPlayer = defaultClient.getJSONObject(client).getBoolean("requireJsPlayer");
        header = defaultClient.getJSONObject(client).getJSONObject("header");

        // API keys are not required, see: https://github.com/TeamNewPipe/NewPipeExtractor/pull/1168
        apiKey = defaultClient.getJSONObject(client).getString("apiKey");
    }
    public JSONObject getInnerTubeContext() throws JSONException {
        return innerTubeContext;
    }
    public void updateInnerTubeContext(JSONObject innerTubeContext, JSONObject extraInfo) throws JSONException {
        for (Iterator<String> it = extraInfo.keys(); it.hasNext(); ) {
            String key = it.next();
            if (innerTubeContext.has(key) && innerTubeContext.get(key) instanceof JSONObject) {
                updateInnerTubeContext(innerTubeContext.getJSONObject(key), extraInfo.getJSONObject(key));
            } else {
                innerTubeContext.put(key, extraInfo.get(key));
            }
        }
    }
    public Map<String, String> getClientHeaders() throws JSONException {
        return getHeaderMap();
    }
    @Deprecated
    public String getClientApiKey() throws JSONException {
        return apiKey;
    }
    public boolean getRequireJsPlayer(){
        return requireJsPlayer;
    }

    private String getBaseUrl(){
        return "https://www.youtube.com/youtubei/v1";
    }

    private String getBaseParam(){
        return "{prettyPrint: \"false\"}";
    }
    private String urlEncode(JSONObject json) throws JSONException, UnsupportedEncodingException {
        StringBuilder query = new StringBuilder();
        for (Iterator<String> it = json.keys(); it.hasNext(); ) {
            String key = it.next();
            String value = json.getString(key);
            query.append(URLEncoder.encode(key, StandardCharsets.UTF_8.name()));
            query.append("=");
            query.append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
            query.append("&");
        }
        if (query.length() != 0) {
            query.setLength(query.length() - 1);
        }
        return query.toString();
    }

    private Map<String, String> getHeaderMap() throws JSONException {
        HashMap<String, String> headers = new HashMap<>();
        Iterator<String> keys = header.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = header.getString(key);
            headers.put(key, value);
        }
        return headers;
    }

    private JSONObject callApi(String endpoint, JSONObject query, JSONObject data) throws Exception {

        String endpointUrl = endpoint + "?" + urlEncode(query);

        ByteArrayOutputStream response = Request.post(endpointUrl, data.toString(), getHeaderMap());
        return new JSONObject(response.toString());
    }

    public JSONObject player(String videoId) throws Exception {
        String endpoint = getBaseUrl() + "/player";
        JSONObject query = new JSONObject(getBaseParam());
        JSONObject context = new JSONObject("{videoId: " + videoId + ", " + "contentCheckOk: \"true\"" + "}");
        updateInnerTubeContext(getInnerTubeContext(), context);
        return callApi(endpoint, query, getInnerTubeContext());
    }

    public JSONObject browse(JSONObject data) throws Exception {
        String endpoint = getBaseUrl() + "/browse";
        JSONObject query = new JSONObject(getBaseParam());
        updateInnerTubeContext(getInnerTubeContext(), data);
        return callApi(endpoint, query, getInnerTubeContext());
    }

    public JSONObject search(String searchQuery, String continuationToken) throws Exception {
        String endpoint = getBaseUrl() + "/search";
        JSONObject query = new JSONObject(getBaseParam());
        JSONObject contextQuery = new JSONObject("{query: " + searchQuery + "}");
        updateInnerTubeContext(getInnerTubeContext(), contextQuery);
        if(!Objects.equals(continuationToken, "")){
            updateInnerTubeContext(getInnerTubeContext(), new JSONObject("{continuation:" + continuationToken + "}"));
        }
        return callApi(endpoint, query, getInnerTubeContext());
    }
}