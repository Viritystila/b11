(ns b11 (:use [overtone.live]) (:require [shadertone.tone :as t]) )



(do
  (defn note->hz [music-note]
    (midi->hz (note music-note)))

  ;Global pulses
  (do
    (defonce root-trg-bus (control-bus)) ;; global metronome pulse
    (defonce root-cnt-bus (control-bus)) ;; global metronome count
    (defonce beat-trg-bus (control-bus)) ;; beat pulse (fraction of root)
    (defonce beat-cnt-bus (control-bus)) ;; beat count
    (def BEAT-FRACTION "Number of global pulses per beat" 4)
    )

  (do
    (defsynth root-trg [rate 100]
      (out:kr root-trg-bus (impulse:kr rate)))

    (defsynth root-cnt []
      (out:kr root-cnt-bus (pulse-count:kr (in:kr root-trg-bus))))

    (defsynth beat-trg [div BEAT-FRACTION]
      (out:kr beat-trg-bus (pulse-divider (in:kr root-trg-bus) div)))

    (defsynth beat-cnt []
      (out:kr beat-cnt-bus (pulse-count (in:kr beat-trg-bus)))))

  (do
    (def r-trg (root-trg))
    (def r-cnt (root-cnt [:after r-trg]))
    (def b-trg (beat-trg [:after r-trg]))
    (def b-cnt (beat-cnt [:after b-trg]))
    (ctl r-trg :rate 100))

                                    ;Buses
  (do
                                    ;Control
    (defonce cbus1 (control-bus 1))
    (defonce cbus2 (control-bus 1))
    (defonce cbus3 (control-bus 1))
    (defonce cbus4 (control-bus 1))
    (defonce cbus5 (control-bus 1))
    (defonce cbus6 (control-bus 1))
    (defonce cbus7 (control-bus 1))
    (defonce cbus8 (control-bus 1))
    (defonce cbus9 (control-bus 1))
    (defonce cbus10 (control-bus 1))
    (defonce cbus11 (control-bus 1))
    (defonce cbus12 (control-bus 1))
    (defonce cbus13 (control-bus 1))
    (defonce cbus14 (control-bus 1))
    (defonce cbus15 (control-bus 1))
    (defonce cbus16 (control-bus 1))
    (defonce cbus17 (control-bus 1))
    (defonce cbus18 (control-bus 1))
    (defonce cbus19 (control-bus 1))
    (defonce cbus20 (control-bus 1))
    (defonce cbus21 (control-bus 1))
    (defonce cbus22 (control-bus 1))
    (defonce cbus23 (control-bus 1))
    (defonce cbus24 (control-bus 1))
    (defonce cbus25 (control-bus 1))
    (defonce cbus26 (control-bus 1))
    (defonce cbus27 (control-bus 1))
    (defonce cbus28 (control-bus 1))
    (defonce cbus29 (control-bus 1))
    (defonce cbus30 (control-bus 1))
    (defonce cbus31 (control-bus 1))
    (defonce cbus32 (control-bus 1))
    ;Audio
    (defonce abus1 (audio-bus))
    (defonce abus2 (audio-bus))
    (defonce abus3 (audio-bus))
    (defonce abus4 (audio-bus))
    (defonce abus5 (audio-bus))
    (defonce abus6 (audio-bus))
    (defonce abus7 (audio-bus))
    (defonce abus8 (audio-bus))
    (defonce abus9 (audio-bus))
    (defonce abus10 (audio-bus))
    (defonce abus11 (audio-bus))
    (defonce abus12 (audio-bus))
    (defonce abus13 (audio-bus))
    (defonce abus14 (audio-bus))
    (defonce abus15 (audio-bus))
    (defonce abus16 (audio-bus))

                                        ;Groups
    (defonce main-g (group "main bus"))
    (defonce early-g (group "early bus" :head main-g))
    (defonce later-g (group "late bus" :after early-g))
    )
                                        ;Buffers
    (defonce buffer-4-1 (buffer 4))
    (defonce buffer-4-2 (buffer 4))
    (defonce buffer-4-3 (buffer 4))
    (defonce buffer-4-4 (buffer 4))

    (defonce buffer-8-1 (buffer 8))
    (defonce buffer-8-2 (buffer 8))
    (defonce buffer-8-3 (buffer 8))
    (defonce buffer-8-4 (buffer 8))

    (defonce buffer-16-1 (buffer 16))
    (defonce buffer-16-2 (buffer 16))
    (defonce buffer-16-3 (buffer 16))
    (defonce buffer-16-4 (buffer 16))

    (defonce buffer-32-1 (buffer 32))
    (defonce buffer-32-2 (buffer 32))
    (defonce buffer-32-3 (buffer 32))
    (defonce buffer-32-4 (buffer 32))
    (defonce buffer-32-5 (buffer 32))
    (defonce buffer-32-6 (buffer 32))
  )

                                        ;Synths
(do
  (ctl r-trg :rate 50)

  (defsynth superSin [outbus 0
                      f1 0 p1 0 a1 0
                      f2 0 p2 0 a2 0
                      f3 0 p3 0 a3 0
                      f4 0 p4 0 a4 0
                      f5 0 p5 0 a5 0
                      f6 0 p6 0 a6 0] (let [sin1 (* (in:kr a1) (sin-osc (in:kr f1) (in:kr p1)))
                                            sin2 (* (in:kr a2) (sin-osc (in:kr f2) (in:kr p2)))
                                            sin3 (* (in:kr a3) (sin-osc (in:kr f3) (in:kr p3)))
                                            sin4 (* (in:kr a4) (sin-osc (in:kr f4) (in:kr p4)))
                                            sin5 (* (in:kr a5) (sin-osc (in:kr f5) (in:kr p5)))
                                            sin6 (* (in:kr a6) (sin-osc (in:kr f6) (in:kr p6)))]
                                        (out outbus (* 1 (normalizer (+ sin1 sin2 sin3 sin4 sin5 sin6))))))

  (def st (superSin [:head early-g] abus1
                    cbus1 cbus2 cbus3
                    cbus4 cbus5 cbus6
                    cbus7 cbus8 cbus9
                    cbus10 cbus11 cbus12
                    cbus13 cbus14 cbus15
                    cbus16 cbus17 cbus18))

                                        ;(kill st)
 ; (kill st)
                                        ; (kill 77)

   (buffer-write! buffer-32-1 [1 1 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0])


  (buffer-write! buffer-32-2 [0 1 4 1 0 1 1 1
                              4 5 6 5 1 1 1 1
                              0 1 4 1 0 1 1 1
                              4 5 6 5 1 1 1 1])

  (defsynth humm [outbus 0  in-bus 0
                  in-bus-ctr 0 beat-buf1 0 beat-buf2 0 attack 0 release 0]
    (let [tr-in (pulse-divider (in:kr in-bus) 1)
         ; tr-in (t-delay:kr tr-in 0)
          ctr-in (in:kr in-bus-ctr)
          pulses (buf-rd:kr 1 beat-buf1 ctr-in)
          f_offset (buf-rd:kr 1 beat-buf2 ctr-in)
          pls    (* tr-in pulses)
          ;trigger (trig pls 0.1)
          ;f_offset (in:kr offset)
          s1 (* 0.5 (sin-osc (* 1 f_offset)))
          s2 (* 0.3 (sin-osc (* 2 f_offset)))
          s3 (* 0.1 (sin-osc (* 3 f_offset)))
          s4 (* 0.05 (sin-osc (* 4 f_offset)))
          sa (* 0.0000005 (saw (* 100 f_offset)) 0)
          env (env-gen (perc (in:kr attack) (in:kr release)) :gate pls)]
          (out 0 (pan2 (normalizer (* (+ (* s1 s2 s3 s4) sa  ) env))))))

  (def kf (humm [:tail early-g] 0 cbus20 cbus21 :in-bus beat-trg-bus :in-bus-ctr beat-cnt-bus :beat-buf1 buffer-32-1 :beat-buf2 buffer-32-2 :attack cbus20 :release cbus21))

  ;(ctl kf :beat-buf2 buffer-32-2 )


  (control-bus-set! cbus20 2.1)
  (control-bus-set! cbus21 3.5)

 ; (kill kf)
  (buffer-write! buffer-32-3 [2 0 1 0 0 0 0 0
                              1 0 0 0 0 0 0 0
                              2 0 1 0 0 0 0 0
                              1 0 0 0 0 0 0 0])


  (defsynth snare [amp 30
                   fraction 1
                   del 0
                   in-trg-bus 0
                   in-bus-ctr 0
                   beat-buf 0
                   out-bus 0
                   del 0
                   attack 0.1
                   sustain 0.1
                   release 0.1]
    (let [tr-in (pulse-divider (in:kr in-trg-bus) fraction)
          tr-in (t-delay:kr tr-in del)
          ctr-in (in:kr in-bus-ctr)
          pulses (buf-rd:kr 1 beat-buf ctr-in)
          pls (* tr-in pulses)
          env (env-gen (lin attack sustain release 0.1) :gate pls)
          snare (* 3 (pink-noise) (apply + (* (decay env [attack release]) [1 release])))
          snare (+ snare (bpf (* 4 snare) 2000))
          snare (clip2 snare 1)]
      (out out-bus (pan2 (* amp snare env)))))

  (def snare_1 (snare [:head early-g]
                      :amp 1
                      :fraction 1
                      :del 0
                      :in-trg-bus beat-trg-bus
                      :in-bus-ctr beat-cnt-bus
                      :beat-buf buffer-32-1
                      :out-bus 0
                      :del 0))

  (ctl snare_1 :amp 0.025 :attack 0.00001 :sustain 0.035 :release 0.385 :beat-buf buffer-32-3 :in-trg-bus beat-trg-bus :in-bus-ctr beat-cnt-bus :del 0.0)

  ;(kill snare_1)

  (pp-node-tree)

  (defsynth envSynth [inbus 0 outbus 0 attack 1 decay 1 sustain 1 release 1 amp 1 trg 1]
    (let [trigger (in:kr trg)
          input  (in:ar inbus)
          env (env-gen (adsr attack decay sustain release amp) :gate trigger)
          ]
      (out outbus (pan2 (* input amp env)))))

  (def evs (envSynth [:tail early-g] :inbus abus1 :trg 1 :amp 1))

  (ctl evs :amp 0.2 :trg cbus19)

  ;(kill evs)

  (do
    (control-bus-set! cbus1 45)
    (control-bus-set! cbus2 (* 0.0 Math/PI))
    (control-bus-set! cbus3 1)

    (control-bus-set! cbus4 41)
    (control-bus-set! cbus5 (* 0.0 Math/PI))
    (control-bus-set! cbus6 2)

    (control-bus-set! cbus7 22)
    (control-bus-set! cbus8 0)
    (control-bus-set! cbus9 1)

    (control-bus-set! cbus10 91)
    (control-bus-set! cbus11 0)
    (control-bus-set! cbus12 1)

    (control-bus-set! cbus13 20)
    (control-bus-set! cbus14 (* Math/PI 0))
    (control-bus-set! cbus15 1)

    (control-bus-set! cbus16 80)
    (control-bus-set! cbus17 0)
    (control-bus-set! cbus18 1))

  (control-bus-set! cbus19 0)



  (def chordBuffer (buffer 16))

  (buffer-write! chordBuffer 4  (map note->hz (chord :A3 :7sus2)))

  (defsynth sinChord [in-bus-ctr 0 idxbuf 0 chordbuf 0 outbus 0 amp 0.05]
    (let [fidx (buf-rd:kr 1 idxbuf (in:kr in-bus-ctr))
          ;fidx (buf-rd:kr 1 idxbuf index)
          f1 (buf-rd:kr 1 chordbuf (+ fidx 0))
          f2 (buf-rd:kr 1 chordbuf (+ fidx 1))
          f3 (buf-rd:kr 1 chordbuf (+ fidx 2))
          s1 (sin-osc f1)
          s2 (sin-osc f2)
          s3 (sin-osc f3)]
          (out outbus (pan2 (* amp (+ s1 s2 s3))))))


  (buffer-write! buffer-32-4 [12 12 12 12 12 12 12 12
                              12 12 12 12 12 12 12 12
                              8 8 8 8 8 8 8 8
                              4 4 4 4 0 0 0 0])

 (def sc1 (sinChord [:tail early-g] beat-cnt-bus buffer-32-4 chordBuffer abus2))

 (ctl sc1 :outbus 0)

 ;(kill sc1)
;(kill 103)
 ;(def b1 (buffer 3))
 ;(buffer-write! b1 (chord :C4 :major))
 ;(chord :C4 :major)

 (defsynth rush [in-bus 0 trg 0]
   (let[audio-in (in in-bus)
        trigger (in:kr trg)
        f_env   (env-gen (perc 1 1 50 2) :gate trigger)
        _       (out:kr trg 0)
        imp    (impulse (* 1 f_env))
        a_env (env-gen (perc 0.015 0.015) :gate imp)
        b_env (env-gen (perc 1 1 50 2) :gate imp)]
        (out 0 (pan2 (+  (* 0.2 audio-in a_env b_env b_env) )))))

                                     ;
 (def rs (rush [:tail early-g] abus1 cbus22))

 (control-bus-set! cbus22 1)

                                        ;
; (kill rs)

  ;(kill 92)
  (pp-node-tree)



  (defsynth kick [freq 80 beat-buf 0 amp 1
                  in-bus-ctr 0 in-trg-bus 0
                  outbus 0 fraction 1 del 0]
    (let [tr-in     (pulse-divider (in:kr in-trg-bus) fraction)
          tr-in     (t-delay:kr tr-in del)
          ctr-in    (in:kr in-bus-ctr)
          pulses    (buf-rd:kr 1 beat-buf ctr-in)
          pls       (* tr-in pulses)
          co-env    (perc 0.001 1 freq -20)
          a-env     (perc 0.001 1 1 -8)
          osc-env   (perc 0.001 pulses freq -8)
          cutoff    (lpf (pink-noise) (+ (env-gen co-env :gate pls) 20))
          sound     (lpf (sin-osc (+ pls (env-gen osc-env :gate pls) 20)) 200)
          env       (env-gen a-env :gate pls)
          output    (* amp (+ cutoff sound) env)]
      (out outbus (pan2 output))))

  (def k1 (kick :beat-buf buffer-32-3 :in-trg-bus beat-trg-bus :in-bus-ctr beat-cnt-bus ))

  (ctl k1 :amp 0.7 :freq 80 :beat-buf buffer-32-1 :fraction 1)

  (control-bus-set! cbus23 0)

 ; (kill k1)

  (buffer-write! buffer-32-5 [1 0 1 0 0 0 0 0
                              1 0 0 0 1 0 0 0
                              1 0 0 0 1 0 0 0
                              1 0 0 0 1 0 0 0])

  (buffer-write! buffer-32-6 [1 1 1 1 1 1 1 1
                              0 0 0 0 2 2 2 2
                              3 3 3 3 4 4 4 4
                              4 4 4 4 0 0 0 0])

  (def bassnotes (buffer 32))

  (buffer-write! bassnotes 4 [(note->hz (note :C3))])

  (defsynth vintage-bass
    [noteidxbuffer 0 notebuffer 0 velocity 80 t 0.6 amp 1 del 0
     in-bus-ctr 0 in-trg-bus 0 beat-buf 0 fraction 1]
    (let [tr-in    (pulse-divider (in:kr in-trg-bus) fraction)
          tr-in    (t-delay:kr tr-in del)
          ctr-in   (in:kr in-bus-ctr)
          pulses   (buf-rd:kr 1 beat-buf ctr-in)
          pls      (* 1 pulses)
          noteidx  (buf-rd:kr 1 noteidxbuffer ctr-in)
          note     (buf-rd:kr 1 notebuffer noteidx)
          freq     note
          sub-freq (- note (midicps 12))
          velocity (/ velocity 127.0)
          sawz1    (* 0.275 (saw [freq (* 1.01 freq)]))
          sawz2    (* 0.75 (saw [(- freq 2) (+ 1 freq)]))
          sqz      (* 0.3 (pulse [sub-freq (- sub-freq 1)]))
          mixed    (* 5 (+ sawz1 sawz2 sqz))
          env      (env-gen (adsr 0.1 3.3 0.4 0.8) :gate pls)
          filt     (* env (moog-ff mixed (* velocity env (+ freq 200)) 2.2))]
    (out 0 (* amp filt))))

  (def bb (vintage-bass :noteidxbuffer buffer-32-6 :notebuffer chordBuffer
           :beat-buf buffer-32-5 :in-trg-bus beat-trg-bus :in-bus-ctr beat-cnt-bus))

  (ctl bb :amp 0.4 :beat-buf buffer-32-5 :notebuffer bassnotes :velocity 70 :t 0.6)

 ; (kill bb)
 ; (stop)

  )
