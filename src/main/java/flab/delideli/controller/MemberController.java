package flab.delideli.controller;

import flab.delideli.service.MemberJoinService;

public class MemberController {

    private final MemberJoinService memberJoinService;

    public MemberController(MemberJoinService memberJoinService) {
        this.memberJoinService = memberJoinService;
    }
}
