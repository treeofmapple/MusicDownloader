package javatube;

import java.util.*;

public class StreamQuery{
    ArrayList<Stream> fmtStreams;
    Map<Integer, Stream> itagIndex = new HashMap<>();
    public StreamQuery(ArrayList<Stream> fmt_streams){
        fmtStreams = fmt_streams;
        for (Stream fmt_stream : fmt_streams) {
            itagIndex.put(fmt_stream.itag, fmt_stream);
        }
    }

    public StreamQuery filter(HashMap<String, String> filters){

        ArrayList<Stream> streamFilter = new ArrayList<>();
        if(filters.containsKey("res")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getResolution(filters.get("res"))));
            }else{
                streamFilter.addAll(getResolution(filters.get("res")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }

        if(filters.containsKey("fps")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getFps(filters.get("fps"))));
            }else{
                streamFilter.addAll(getFps(filters.get("fps")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }

        if(filters.containsKey("mineType")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getMineType(filters.get("mineType"))));
            }else{
                streamFilter.addAll(getMineType(filters.get("mineType")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }

        if(filters.containsKey("type")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getType(filters.get("type"))));
            }else{
                streamFilter.addAll(getType(filters.get("type")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }

        if(filters.containsKey("subType")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getSubtype(filters.get("subType"))));
            }else{
                streamFilter.addAll(getSubtype(filters.get("subType")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }

        if(filters.containsKey("abr")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getAbr(filters.get("abr"))));
            }else{
                streamFilter.addAll(getAbr(filters.get("abr")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }

        }

        if(filters.containsKey("videoCodec")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getVideoCodec(filters.get("videoCodec"))));
            }else{
                streamFilter.addAll(getVideoCodec(filters.get("videoCodec")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }
        if(filters.containsKey("audioCodec")){
            if(!streamFilter.isEmpty()){
                streamFilter.retainAll(new ArrayList<>(getAudioCodec(filters.get("audioCodec"))));
            }else{
                streamFilter.addAll(getAudioCodec(filters.get("audioCodec")));
            }
            if(streamFilter.isEmpty()){
                filters.clear();
            }
        }
        if(filters.containsKey("onlyAudio")){
            if(Objects.equals(filters.get("onlyAudio"), "true")){
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(onlyAudio()));
                }else{
                    streamFilter.addAll(onlyAudio());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            }
        }
        if(filters.containsKey("onlyVideo")){
            if(Objects.equals(filters.get("onlyVideo"), "true")){
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(onlyVideo()));
                }else{
                    streamFilter.addAll(onlyVideo());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            }
        }
        if(filters.containsKey("progressive")){
            if(Objects.equals(filters.get("progressive"), "true")){
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(getProgressive()));
                }else{
                    streamFilter.addAll(getProgressive());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            }else if (Objects.equals(filters.get("progressive"), "false")){
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(getAdaptive()));
                }else{
                    streamFilter.addAll(getAdaptive());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            }
        }
        if(filters.containsKey("adaptive")){
            if(Objects.equals(filters.get("adaptive"), "true")){
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(getAdaptive()));
                }else{
                    streamFilter.addAll(getAdaptive());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            } else if (Objects.equals(filters.get("adaptive"), "false")) {
                if(!streamFilter.isEmpty()){
                    streamFilter.retainAll(new ArrayList<>(getProgressive()));
                }else{
                    streamFilter.addAll(getProgressive());
                }
                if(streamFilter.isEmpty()){
                    filters.clear();
                }
            }
        }

        return new StreamQuery(streamFilter);
    }

    private ArrayList<Stream> getResolution(String re){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.resolution, re)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getFps(String fps){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.fps, Integer.parseInt(fps))){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getMineType(String mineType){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.mimeType, mineType)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getType(String type){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.type, type)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getSubtype(String subtype){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.subType, subtype)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getAbr(String abr){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.abr, abr)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getVideoCodec(String videoCodec){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.videoCodec, videoCodec)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getAudioCodec(String audioCodec){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(Objects.equals(st.audioCodec, audioCodec)){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> onlyAudio(){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if((st.includeAudioTrack()) && (!st.includeVideoTrack())){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> onlyVideo(){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if((st.includeVideoTrack() && (!st.includeAudioTrack()))){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getProgressive(){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(st.isProgressive()){
                filter.add(st);
            }
        }
        return filter;
    }

    private ArrayList<Stream> getAdaptive(){
        ArrayList<Stream> filter = new ArrayList<>();
        for(Stream st : fmtStreams){
            if(st.isAdaptive()){
                filter.add(st);
            }
        }
        return filter;
    }

    public Stream getFirst(){
        return fmtStreams.get(0);
    }

    public Stream getLast(){
        return fmtStreams.get(fmtStreams.size() - 1);
    }

    public Stream getOnlyAudio(){
        HashMap<String, String> filters = new HashMap<>();
        filters.put("onlyAudio", "true");
        filters.put("subType", "mp4");
        return filter(filters).getLast();
    }

    public Stream getLowestResolution(){
        HashMap<String, String> filters = new HashMap<>();
        filters.put("progressive", "true");
        filters.put("subType", "mp4");
        return filter(filters).getFirst();
    }

    public Stream getHighestResolution(){
        HashMap<String, String> filters = new HashMap<>();
        filters.put("progressive", "true");
        filters.put("subType", "mp4");
        return filter(filters).getLast();
    }

}
