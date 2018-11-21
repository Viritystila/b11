(ns b11 (:use [overtone.live]) (:require [shadertone.tone :as t]) )



(do
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
  )



                                        ;Synths
(do
  (ctl r-trg :rate 12)

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

   (buffer-write! buffer-32-1 [1 0 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0
                              1 0 0 0 0 0 0 0])


  (buffer-write! buffer-32-2 [0 1 4 1 0 1 4 1
                              3 4 5 3 1 0 1 0
                              0 2 3 4 0 0 0 0
                              4 5 6 5 4 3 1 0])

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
          sa (* 0.0000005 (saw 100))
          env (env-gen (perc (in:kr attack) (in:kr release)) :gate pls)]
          (out 0 (pan2 (normalizer (* (+ (* s1 s2 s3 s4) sa  ) env))))))

  (def kf (humm 0 cbus20 cbus21 :in-bus beat-trg-bus :in-bus-ctr beat-cnt-bus :beat-buf1 buffer-32-1 :beat-buf2 buffer-32-2 :attack cbus20 :release cbus21))

  ;(ctl kf :beat-buf2 buffer-32-2 )


  (control-bus-set! cbus20 0.2)
  (control-bus-set! cbus21 2.5)

 ; (kill kf)

  (pp-node-tree)

  (defsynth envSynth [inbus 0 outbus 0 attack 1 decay 1 sustain 1 release 1 amp 1 trg 1]
    (let [trigger (in:kr trg)
          input  (in:ar inbus)
          env (env-gen (adsr attack decay sustain release amp) :gate trigger)
          ]
      (out outbus (pan2 (* input amp env)))))

  (def evs (envSynth [:tail early-g] :inbus abus1 :trg 1 :amp 1))

  (ctl evs :amp 0.1)

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

  (control-bus-set! cbus19 1)



  ;(kill st)


  (pp-node-tree))


(stop)
