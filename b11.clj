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
    (ctl r-trg :rate 100)

    )

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


                                        ;Control synths
(do
  (defsynth sin-out [freq1 1 freq2 1 scaler 1 out-control-bus 0]
    (out:kr out-control-bus (+ (in:kr freq1) (* scaler (sin-osc:kr freq2)) )))

  (def sin-out_1 (sin-out [:tail early-g] :freq1 cbus5 :freq2 1 :scaler 5 :out-control-bus cbus6))

  (ctl sin-out_1 :freq2 2 :scaler 1)

                                        ;Synths
  (defsynth sin-wave [amp 1 freq 22 phase 0 out-bus 0]
    (let [amp_in (in:kr amp)
          freq_in (in:kr freq)
          phase_in (in:kr phase)
          src (sin-osc freq_in phase_in 0)]
      (out out-bus (* amp_in src))))

  (def sin-wave_1 (sin-wave [:tail early-g] :amp cbus1 :freq cbus2 :phase cbus3 :out-bus abus1))

  (def sin-wave_2 (sin-wave [:tail early-g] :amp cbus4 :freq cbus6 :phase 0 :out-bus abus2))


  (ctl r-trg :rate 12)

  (defsynth superSin [f1 0 p1 0 a1 0
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
                                        (out 0 (pan2 (normalizer (+ sin1 sin2 sin3 sin4 sin5 sin6))))))

  (def st (superSin cbus1 cbus2 cbus3
                    cbus4 cbus5 cbus6
                    cbus7 cbus8 cbus9
                    cbus10 cbus11 cbus12
                    cbus13 cbus14 cbus15
                    cbus16 cbus17 cbus18))

                                        ;(kill st)

  (control-bus-set! cbus1 45)
  (control-bus-set! cbus2 (* 0.0 Math/PI))
  (control-bus-set! cbus3 1)

  (control-bus-set! cbus4 43)
  (control-bus-set! cbus5 (* 0.0 Math/PI))
  (control-bus-set! cbus6 1)

  (control-bus-set! cbus7 22)
  (control-bus-set! cbus8 0)
  (control-bus-set! cbus9 1)

  (control-bus-set! cbus10 91)
  (control-bus-set! cbus11 0)
  (control-bus-set! cbus12 1)

  (control-bus-set! cbus13 20)
  (control-bus-set! cbus14 (* Math/PI 0))
  (control-bus-set! cbus15 1)

  (control-bus-set! cbus16 100)
  (control-bus-set! cbus17 0)
  (control-bus-set! cbus18 1)

  (stop)
  ;(kill st)
  (pp-node-tree)
  )
